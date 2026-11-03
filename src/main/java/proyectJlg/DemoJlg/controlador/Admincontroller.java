package proyectJlg.DemoJlg.controlador;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import proyectJlg.DemoJlg.servicios.CotizacionService;

@Controller
public class AdminController {

    private final CotizacionService cotizacionService;

    public AdminController(CotizacionService cotizacionService) {
        this.cotizacionService = cotizacionService;
    }

    @GetMapping("/admin/dashboard")
    public String dashboardAdmin(Authentication authentication, Model model) {
        model.addAttribute("username", authentication.getName());
        // KPIs reales desde MongoDB Atlas
        model.addAttribute("totalCotizaciones", cotizacionService.contarTodas());
        model.addAttribute("cotizacionesPendientes", cotizacionService.contarPorEstado("PENDIENTE"));
        model.addAttribute("cotizacionesAprobadas", cotizacionService.contarPorEstado("APROBADA"));
        model.addAttribute("sumaTotal", cotizacionService.sumarValorTotal());
        return "admin/dashboard";
    }
}