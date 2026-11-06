package proyectJlg.DemoJlg.servicios;

import org.springframework.stereotype.Service;
import proyectJlg.DemoJlg.modelo.SolicitudVisita;
import proyectJlg.DemoJlg.repositorio.SolicitudVisitaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SolicitudVisitaService {
    
    private final SolicitudVisitaRepository solicitudRepository;
    
    public SolicitudVisitaService(SolicitudVisitaRepository solicitudRepository) {
        this.solicitudRepository = solicitudRepository;
    }
    
    // Guardar una nueva solicitud
    public SolicitudVisita guardarSolicitud(SolicitudVisita solicitud) {
        return solicitudRepository.save(solicitud);
    }
    
    // Obtener todas las solicitudes de un usuario
    public List<SolicitudVisita> obtenerSolicitudesPorUsuario(String correoUsuario) {
        return solicitudRepository.findByCorreoUsuario(correoUsuario);
    }
    
    // Obtener todas las solicitudes (para ADMIN)
    public List<SolicitudVisita> obtenerTodasSolicitudes() {
        return solicitudRepository.findAll();
    }
    
    // Obtener una solicitud por ID
    public Optional<SolicitudVisita> obtenerPorId(String id) {
        return solicitudRepository.findById(id);
    }
    
    // Actualizar estado de una solicitud
    public SolicitudVisita actualizarEstado(String id, String nuevoEstado) {
        Optional<SolicitudVisita> solicitud = solicitudRepository.findById(id);
        if (solicitud.isPresent()) {
            SolicitudVisita s = solicitud.get();
            s.setEstado(nuevoEstado);
            return solicitudRepository.save(s);
        }
        return null;
    }
}