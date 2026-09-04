package proyectJlg.DemoJlg.controlador;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import proyectJlg.DemoJlg.modelo.Usuario;
import proyectJlg.DemoJlg.servicios.UsuarioService;

@Controller
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // Mostrar formulario de registro
    @GetMapping("/registro")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registro";
    }

    // Procesar registro
    @PostMapping("/registro")
    public String registrarUsuario(
            @ModelAttribute("usuario") Usuario usuario) {

        usuarioService.registrarUsuario(usuario);

        return "redirect:/login?registroExitoso";
    }
    @GetMapping("/user/dashboard")
     public String dashboardUsuario() {
       return "user/dashboard";
}
}