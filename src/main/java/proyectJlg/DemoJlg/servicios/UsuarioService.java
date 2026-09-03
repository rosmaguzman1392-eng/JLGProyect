package proyectJlg.DemoJlg.servicios;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import proyectJlg.DemoJlg.modelo.Rol;
import proyectJlg.DemoJlg.modelo.Usuario;
import proyectJlg.DemoJlg.repositorio.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario registrarUsuario(Usuario usuario) {

        if (usuarioRepository.existsByCorreo(usuario.getCorreo())) {
            throw new IllegalArgumentException(
                "El correo ya se encuentra registrado"
            );
        }

        usuario.setPassword(
            passwordEncoder.encode(usuario.getPassword())
        );

        if (usuario.getRol() == null) {
            usuario.setRol(Rol.USER);
        }

        return usuarioRepository.save(usuario);
    }
}