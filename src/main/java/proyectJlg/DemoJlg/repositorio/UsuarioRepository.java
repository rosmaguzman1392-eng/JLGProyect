package proyectJlg.DemoJlg.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import proyectJlg.DemoJlg.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCorreo(String correo);

    boolean existsByCorreo(String correo);
}