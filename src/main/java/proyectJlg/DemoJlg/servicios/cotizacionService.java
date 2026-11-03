package proyectJlg.DemoJlg.servicios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import proyectJlg.DemoJlg.modelo.Cotizacion;
import proyectJlg.DemoJlg.repositorio.CotizacionRepository;

import java.util.Optional;

@Service
public class CotizacionService {

    private final CotizacionRepository cotizacionRepository;

    public CotizacionService(CotizacionRepository cotizacionRepository) {
        this.cotizacionRepository = cotizacionRepository;
    }

    // Listado con paginación
    public Page<Cotizacion> listarTodas(Pageable pageable) {
        return cotizacionRepository.findAll(pageable);
    }

    // Búsqueda por cliente (buscador de la vista)
    public Page<Cotizacion> buscarPorCliente(String cliente, Pageable pageable) {
        return cotizacionRepository.findByClienteContainingIgnoreCase(cliente, pageable);
    }

    // Filtro por estado
    public Page<Cotizacion> filtrarPorEstado(String estado, Pageable pageable) {
        return cotizacionRepository.findByEstado(estado, pageable);
    }

    // KPIs del dashboard
    public long contarTodas() {
        return cotizacionRepository.count();
    }

    public long contarPorEstado(String estado) {
        return cotizacionRepository.countByEstado(estado);
    }

    public double sumarValorTotal() {
        return cotizacionRepository.findAll()
                .stream()
                .mapToDouble(c -> c.getValorTotal() == null ? 0 : c.getValorTotal())
                .sum();
    }
    public Optional<Cotizacion> obtenerPorId(String id) {
        return cotizacionRepository.findById(id);
    }

    public Cotizacion actualizarEstado(String id, String nuevoEstado) {
        Optional<Cotizacion> cotizacion = cotizacionRepository.findById(id);
        if (cotizacion.isPresent()) {
            Cotizacion c = cotizacion.get();
            c.setEstado(nuevoEstado);
            return cotizacionRepository.save(c);
        }
        return null;
    }

    public Cotizacion guardar(Cotizacion cotizacion) {
        return cotizacionRepository.save(cotizacion);
    }
}