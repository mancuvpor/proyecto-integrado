package manuel.proyectointegrado.repositories;

import manuel.proyectointegrado.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findUsuariosByUsername(String username);

    Optional<Usuario> findUsuarioByEmail(String email);

}
