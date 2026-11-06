package proyectJlg.DemoJlg.controlador;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import proyectJlg.DemoJlg.modelo.SolicitudVisita;
import proyectJlg.DemoJlg.servicios.SolicitudVisitaService;

import java.time.LocalDate;

@Controller
@RequestMapping("/user")
public class SolicitudVisitaController {
    
    private final SolicitudVisitaService solicitudService;
    
    public SolicitudVisitaController(SolicitudVisitaService solicitudService) {
        this.solicitudService = solicitudService;
    }
    
    // Mostrar formulario de solicitud
    @GetMapping("/solicitud-visita")
    public String mostrarFormulario(Model model) {
        model.addAttribute("solicitud", new SolicitudVisita());
        return "user/solicitud-visita"; // Vista Thymeleaf
    }
    
    // Procesar el formulario
    @PostMapping("/solicitud-visita")
    public String procesarSolicitud(SolicitudVisita solicitud, Authentication authentication, Model model) {
        // Obtener el correo del usuario autenticado
        String correo = authentication.getName();
        solicitud.setCorreoUsuario(correo);
        solicitud.setFechaCreacion(LocalDate.now());
        solicitud.setEstado("PENDIENTE");
        
        // Guardar en MongoDB
        solicitudService.guardarSolicitud(solicitud);
        
        // Mostrar mensaje de éxito
        model.addAttribute("mensaje", "Solicitud enviada exitosamente. Nos pondremos en contacto pronto.");
        model.addAttribute("solicitud", new SolicitudVisita()); // Limpiar formulario
        
        return "user/solicitud-visita";
    }
    
    // Mostrar historial de solicitudes del usuario
    @GetMapping("/mis-solicitudes")
    public String mostrarMisSolicitudes(Authentication authentication, Model model) {
        String correo = authentication.getName();
        var solicitudes = solicitudService.obtenerSolicitudesPorUsuario(correo);
        model.addAttribute("solicitudes", solicitudes);
        return "user/mis-solicitudes"; // Vista Thymeleaf
    }
}