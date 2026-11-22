package proyectJlg.DemoJlg.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.ConditionalOperators;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.mongodb.client.MongoClient;

import proyectJlg.DemoJlg.modelo.CategoriaProductoDTO;
import proyectJlg.DemoJlg.modelo.CotizacionDetalleDTO;
import proyectJlg.DemoJlg.modelo.ResumenDTO;
import proyectJlg.DemoJlg.modelo.TopClienteDTO;
import proyectJlg.DemoJlg.modelo.TopProductoDTO;

@Service
public class AnaliticaService {

    private final MongoTemplate modeloMongoTemplate;

    // ✅ Ya no usamos @Qualifier: creamos el template internamente
    // con el mismo MongoClient de Atlas, pero apuntando a la base del modelo
    public AnaliticaService(MongoClient mongoClient,
                            @Value("${jlg.mongodb.modelo-database}") String nombreBaseModelo) {
        this.modeloMongoTemplate = new MongoTemplate(mongoClient, nombreBaseModelo);
    }
    // ===== 1) RESUMEN GENERAL: $group + $cond =====
    public ResumenDTO resumenGeneral() {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.group()
                        .count().as("totalCotizaciones")
                        .sum("valorTotal").as("valorTotalCotizado")
                        .avg("valorTotal").as("ticketPromedio")
                        .sum(ConditionalOperators.Cond
                                .when(Criteria.where("estado").is("Aprobada"))
                                .then(1).otherwise(0))
                        .as("cotizacionesAprobadas")
        );
        List<ResumenDTO> r = modeloMongoTemplate
                .aggregate(agg, "cotizaciones", ResumenDTO.class).getMappedResults();
        return r.isEmpty() ? new ResumenDTO() : r.get(0);
    }

    // ===== 2) TOP CLIENTES: $match + $group + $sort + $limit =====
    public List<TopClienteDTO> topClientes(int limite) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("valorTotal").gt(0)),
                Aggregation.group("nombreCliente")
                        .count().as("numCotizaciones")
                        .sum("valorTotal").as("totalCotizado"),
                Aggregation.project()
                        .and("_id").as("cliente")
                        .and("numCotizaciones").as("numCotizaciones")
                        .and("totalCotizado").as("totalCotizado"),
                Aggregation.sort(Sort.Direction.DESC, "totalCotizado"),
                Aggregation.limit(limite)
        );
        return modeloMongoTemplate
                .aggregate(agg, "cotizaciones", TopClienteDTO.class).getMappedResults();
    }

    // ===== 3) COTIZACIONES + CLIENTE: $lookup + $unwind + $sort =====
    public List<CotizacionDetalleDTO> cotizacionesConCliente(String estado) {
        List<AggregationOperation> ops = new ArrayList<>();
        if (estado != null && !estado.isBlank()) {
            ops.add(Aggregation.match(Criteria.where("estado").is(estado))); // $match dinámico
        }
        ops.add(Aggregation.lookup("clientes", "clienteId", "_id", "detalleCliente")); // JOIN por ObjectId
        ops.add(Aggregation.unwind("detalleCliente"));
        ops.add(Aggregation.project()
                .and("_id").as("id")
                .and("numeroCotizacion").as("numeroCotizacion")
                .and("nombreCliente").as("cliente")
                .and("fechaCotizacion").as("fechaCotizacion")
                .and("valorTotal").as("valorTotal")
                .and("estado").as("estado")
                .and("detalleCliente.tipoCliente").as("tipoCliente")
                .and("detalleCliente.direccion").as("direccion"));
        ops.add(Aggregation.sort(Sort.Direction.DESC, "fechaCotizacion"));
        ops.add(Aggregation.limit(10));
        return modeloMongoTemplate
                .aggregate(Aggregation.newAggregation(ops), "cotizaciones", CotizacionDetalleDTO.class)
                .getMappedResults();
    }

    // ===== 4) TOP PRODUCTOS: $unwind + $group + $sort (array embebido en cotizaciones) =====
    // ⚠️ SI AL EXPANDIR productos[] EN COMPASS VES OTROS NOMBRES, cambia SOLO estas 3 cadenas:
    //    "productos.nombre"  /  "productos.cantidad"  /  "productos.valorTotalProducto"
    public List<TopProductoDTO> topProductos(int limite) {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.unwind("productos"),
                Aggregation.group("productos.nombre")
                        .sum("productos.cantidad").as("cantidadTotal")
                        .sum("productos.valorTotal").as("valorTotal"),
                Aggregation.project()
                        .and("_id").as("producto")
                        .and("cantidadTotal").as("cantidadTotal")
                        .and("valorTotal").as("valorTotal"),
                Aggregation.sort(Sort.Direction.DESC, "valorTotal"),
                Aggregation.limit(limite)
        );
        return modeloMongoTemplate
                .aggregate(agg, "cotizaciones", TopProductoDTO.class).getMappedResults();
    }

    // ===== 5) PRODUCTOS POR CATEGORÍA: $match + $group sobre el CATÁLOGO productos =====
    public List<CategoriaProductoDTO> productosPorCategoria() {
        Aggregation agg = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("estado").is("Activo")),
                Aggregation.group("categoria").count().as("totalProductos"),
                Aggregation.project()
                        .and("_id").as("categoria")
                        .and("totalProductos").as("totalProductos"),
                Aggregation.sort(Sort.Direction.DESC, "totalProductos")
        );
        return modeloMongoTemplate
                .aggregate(agg, "productos", CategoriaProductoDTO.class).getMappedResults();
    }
}