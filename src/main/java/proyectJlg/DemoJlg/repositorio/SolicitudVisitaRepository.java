package proyectJlg.DemoJlg.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import proyectJlg.DemoJlg.modelo.SolicitudVisita;

import java.util.List;


public interface SolicitudVisitaRepository extends MongoRepository<SolicitudVisita, String> {
    
    // Buscar todas las solicitudes de un usuario específico
    List<SolicitudVisita> findByCorreoUsuario(String correoUsuario);
    
    // Buscar solicitudes por estado
    List<SolicitudVisita> findByEstado(String estado);
    
    // Buscar solicitudes de un usuario con un estado específico
    List<SolicitudVisita> findByCorreoUsuarioAndEstado(String correoUsuario, String estado);
}