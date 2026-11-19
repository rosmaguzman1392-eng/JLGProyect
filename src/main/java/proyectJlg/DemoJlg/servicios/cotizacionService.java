package proyectJlg.DemoJlg.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import proyectJlg.DemoJlg.modelo.Cotizacion;
import proyectJlg.DemoJlg.repositorio.CotizacionRepository;

@Service
public class CotizacionService {

    private final CotizacionRepository cotizacionRepository;

    public CotizacionService(CotizacionRepository cotizacionRepository) {
        this.cotizacionRepository = cotizacionRepository;
    }

    public Page<Cotizacion> listarTodas(Pageable pageable) {
        return cotizacionRepository.findAll(pageable);
    }

    public Page<Cotizacion> buscarPorCliente(String cliente, Pageable pageable) {
        return cotizacionRepository.findByClienteContainingIgnoreCase(cliente, pageable);
    }

    public Page<Cotizacion> filtrarPorEstado(String estado, Pageable pageable) {
        return cotizacionRepository.findByEstado(estado, pageable);
    }

    public List<Cotizacion> listarAprobadas() {
        return cotizacionRepository.findByEstado("APROBADA");
    }

    public long contarTodas() {
        return cotizacionRepository.count();
    }

    public long contarPorEstado(String estado) {
        return cotizacionRepository.countByEstado(estado);
    }

    public double sumarValorTotal() {
        return cotizacionRepository.findAll().stream()
                .mapToDouble(c -> c.getValorTotal() == null ? 0 : c.getValorTotal())
                .sum();
    }

    public Optional<Cotizacion> obtenerPorId(String id) {
        return cotizacionRepository.findById(id);
    }

    public Cotizacion guardar(Cotizacion cotizacion) {
        return cotizacionRepository.save(cotizacion);
    }
}