package manuel.proyectointegrado.services;

import manuel.proyectointegrado.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario insertarUsuario(Usuario usuario);

    Usuario getUsuarioByUsername(String nombre_usuario);

    List<Usuario> getAllUsuarios();

    Usuario actualizarUsuario(Usuario usuario);

    void eliminarUsuario(int id);

    Optional<Usuario> findUsuariosById(int id);

    Optional<Usuario> findUsuariosByUsername(String nombre_usuario);

    Optional<Usuario> findUsuariosByEmail(String correo_electronico);

}
