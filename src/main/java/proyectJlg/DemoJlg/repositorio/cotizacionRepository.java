package proyectJlg.DemoJlg.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import proyectJlg.DemoJlg.modelo.Cotizacion;

public interface CotizacionRepository extends MongoRepository<Cotizacion, String> {
    Page<Cotizacion> findByClienteContainingIgnoreCase(String cliente, Pageable pageable);
    Page<Cotizacion> findByEstado(String estado, Pageable pageable);
    List<Cotizacion> findByEstado(String estado);
    long countByEstado(String estado);
}