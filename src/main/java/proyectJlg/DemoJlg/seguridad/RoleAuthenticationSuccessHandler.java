package proyectJlg.DemoJlg.seguridad;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class RoleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        
        boolean esAdmin = false;
        
        // Verificar los roles del usuario autenticado
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                esAdmin = true;
                break;
            }
        }
        
        // Redirigir según el rol
        if (esAdmin) {
            response.sendRedirect("/admin/dashboard");
        } else {
            response.sendRedirect("/user/dashboard");
        }
    }
}