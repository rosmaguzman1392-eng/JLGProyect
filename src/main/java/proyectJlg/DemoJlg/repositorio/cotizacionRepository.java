package proyectJlg.DemoJlg.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import proyectJlg.DemoJlg.modelo.Cotizacion;

public interface CotizacionRepository extends MongoRepository<Cotizacion, String> {

    // Búsqueda por cliente (buscador de la vista)
    Page<Cotizacion> findByClienteContainingIgnoreCase(String cliente, Pageable pageable);

    // Filtro por estado
    Page<Cotizacion> findByEstado(String estado, Pageable pageable);

    // KPIs del dashboard
    long countByEstado(String estado);
}