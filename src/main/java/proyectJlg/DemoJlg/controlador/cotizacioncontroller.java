package proyectJlg.DemoJlg.controlador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import proyectJlg.DemoJlg.modelo.Cotizacion;
import proyectJlg.DemoJlg.servicios.CotizacionService;

@Controller
@RequestMapping("/admin")
public class CotizacionController {

    private final CotizacionService cotizacionService;

    public CotizacionController(CotizacionService cotizacionService) {
        this.cotizacionService = cotizacionService;
    }

    @GetMapping("/cotizaciones")
    public String listarCotizaciones(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(required = false) String buscar,
            @RequestParam(required = false) String estado,
            Model model) {

        Pageable paginacion = PageRequest.of(pagina, 15, Sort.by("fechaCotizacion").descending());
        Page<Cotizacion> paginaCotizaciones;

        if (buscar != null && !buscar.isBlank()) {
            paginaCotizaciones = cotizacionService.buscarPorCliente(buscar, paginacion);
        } else if (estado != null && !estado.isBlank()) {
            paginaCotizaciones = cotizacionService.filtrarPorEstado(estado, paginacion);
        } else {
            paginaCotizaciones = cotizacionService.listarTodas(paginacion);
        }

        model.addAttribute("cotizaciones", paginaCotizaciones.getContent());
        model.addAttribute("paginaActual", paginaCotizaciones.getNumber());
        model.addAttribute("totalPaginas", paginaCotizaciones.getTotalPages());
        model.addAttribute("totalRegistros", paginaCotizaciones.getTotalElements());
        model.addAttribute("buscar", buscar);
        model.addAttribute("estado", estado);
        // KPIs de la vista de cotizaciones
        model.addAttribute("cotizacionesPendientes", cotizacionService.contarPorEstado("PENDIENTE"));
        model.addAttribute("cotizacionesAprobadas", cotizacionService.contarPorEstado("APROBADA"));

        return "admin/cotizaciones";
    }
}