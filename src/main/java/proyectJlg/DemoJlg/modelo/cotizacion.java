package proyectJlg.DemoJlg.modelo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.util.List;

@Document(collection = "cotizaciones")
public class Cotizacion {

    @Id
    private String id;

    private String cliente;

    @Field("fechaCotizacion")
    private String fechaCotizacion;

    @Field("valorTotal")
    private Double valorTotal;

    private String estado;

    private List<Producto> productos;

    // ===== CLASE INTERNA: Producto (documento embebido) =====
    public static class Producto {

        private String nombre;
        private Double cantidad;

        @Field("valorUnitario")
        private Double valorUnitario;

        @Field("valorTotalProducto")
        private Double valorTotalProducto;

        // Constructor vacío (requerido por MongoDB)
        public Producto() {
        }

        // Getters y Setters
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }

        public Double getCantidad() { return cantidad; }
        public void setCantidad(Double cantidad) { this.cantidad = cantidad; }

        public Double getValorUnitario() { return valorUnitario; }
        public void setValorUnitario(Double valorUnitario) { this.valorUnitario = valorUnitario; }

        public Double getValorTotalProducto() { return valorTotalProducto; }
        public void setValorTotalProducto(Double valorTotalProducto) { this.valorTotalProducto = valorTotalProducto; }
    }

    // ===== CONSTRUCTORES =====
    public Cotizacion() {
        this.estado = "PENDIENTE";
    }

    // ===== GETTERS Y SETTERS =====
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }

    public String getFechaCotizacion() { return fechaCotizacion; }
    public void setFechaCotizacion(String fechaCotizacion) { this.fechaCotizacion = fechaCotizacion; }

    public Double getValorTotal() { return valorTotal; }
    public void setValorTotal(Double valorTotal) { this.valorTotal = valorTotal; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public List<Producto> getProductos() { return productos; }
    public void setProductos(List<Producto> productos) { this.productos = productos; }
}