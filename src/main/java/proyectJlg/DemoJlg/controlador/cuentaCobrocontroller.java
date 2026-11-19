package proyectJlg.DemoJlg.controlador;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import proyectJlg.DemoJlg.modelo.CuentaCobro;
import proyectJlg.DemoJlg.servicios.CotizacionService;
import proyectJlg.DemoJlg.servicios.CuentaCobroService;

@Controller
@RequestMapping("/admin")
public class CuentaCobroController {

    private final CuentaCobroService cuentaCobroService;
    private final CotizacionService cotizacionService;

    public CuentaCobroController(CuentaCobroService cuentaCobroService,
                                 CotizacionService cotizacionService) {
        this.cuentaCobroService = cuentaCobroService;
        this.cotizacionService = cotizacionService;
    }

    @GetMapping("/cuentas-cobro/nueva")
    public String nueva(Model model) {
        model.addAttribute("cuentaCobro", new CuentaCobro());
        model.addAttribute("cotizacionesAprobadas", cotizacionService.listarAprobadas());
        return "admin/cuenta-cobro";
    }

    @PostMapping("/cuentas-cobro/guardar")
    public String guardar(CuentaCobro cuentaCobro) {
        if (cuentaCobro.getFechaEmision() == null) {
            cuentaCobro.setFechaEmision(LocalDate.now());
        }
        if (cuentaCobro.getEstado() == null || cuentaCobro.getEstado().isBlank()) {
            cuentaCobro.setEstado("PENDIENTE_DE_PAGO");
        }
        cuentaCobroService.guardar(cuentaCobro);
        return "redirect:/admin/cuentas-cobro/nueva?exito";
    }
}