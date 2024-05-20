package manuel.proyectointegrado.repositories;

import manuel.proyectointegrado.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findUsuariosById(int id);

    Usuario findUsuariosByUsername(String username);

    Usuario findUsuarioByEmail(String email);

}
