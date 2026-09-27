package proyectJlg.DemoJlg.repositorio;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import proyectJlg.DemoJlg.modelo.Usuario;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    Optional<Usuario> findByCorreo(String correo);

    boolean existsByCorreo(String correo);
}