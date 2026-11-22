package proyectJlg.DemoJlg.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import proyectJlg.DemoJlg.servicios.AnaliticaService;

@Controller
@RequestMapping("/admin")
public class AnaliticaController {

    private final AnaliticaService analiticaService;

    public AnaliticaController(AnaliticaService analiticaService) {
        this.analiticaService = analiticaService;
    }

    @GetMapping("/analitica")
    public String analitica(@RequestParam(required = false) String estado, Model model) {
        model.addAttribute("resumen", analiticaService.resumenGeneral());
        model.addAttribute("topClientes", analiticaService.topClientes(5));
        model.addAttribute("cotizacionesDetalle", analiticaService.cotizacionesConCliente(estado));
        model.addAttribute("topProductos", analiticaService.topProductos(8));
        model.addAttribute("productosPorCategoria", analiticaService.productosPorCategoria());
        model.addAttribute("estadoFiltro", estado);
        return "admin/analitica";
    }
}