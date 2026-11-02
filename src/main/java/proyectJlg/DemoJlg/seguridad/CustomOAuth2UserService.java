package proyectJlg.DemoJlg.seguridad;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import proyectJlg.DemoJlg.modelo.Rol;
import proyectJlg.DemoJlg.modelo.Usuario;
import proyectJlg.DemoJlg.repositorio.UsuarioRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UsuarioRepository usuarioRepository;

    public CustomOAuth2UserService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest)
            throws OAuth2AuthenticationException {

        // Obtener información del usuario desde Google
        OAuth2User oauth2User = super.loadUser(userRequest);

        String email = oauth2User.getAttribute("email");
        String name = oauth2User.getAttribute("name");

        // Buscar usuario en MongoDB
        Optional<Usuario> usuarioExistente =
                usuarioRepository.findByCorreo(email);

        Usuario usuario;

        if (usuarioExistente.isEmpty()) {

            // Crear usuario nuevo
            usuario = new Usuario();

            usuario.setCorreo(email);
            usuario.setNombre(
                    name != null ? name : email.split("@")[0]
            );
            usuario.setApellido("");
            usuario.setPassword("");
            usuario.setRol(Rol.USER);

            usuario = usuarioRepository.save(usuario);

        } else {

            // Usuario ya registrado
            usuario = usuarioExistente.get();
        }

        // Crear las autoridades de Spring Security
        Collection<GrantedAuthority> authorities =
                new ArrayList<>();

        // Agregar el rol almacenado en MongoDB
        authorities.add(
                new SimpleGrantedAuthority(
                        "ROLE_" + usuario.getRol().name()
                )
        );

        // Devolver usuario autenticado con su rol
        return new DefaultOAuth2User(
                authorities,
                oauth2User.getAttributes(),
                "sub"
        );
    }
}
