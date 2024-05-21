package manuel.proyectointegrado.services.impl;

import manuel.proyectointegrado.models.Usuario;
import manuel.proyectointegrado.repositories.UsuarioRepository;
import manuel.proyectointegrado.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Usuario insertarUsuario(Usuario usuario) {
        if (usuario != null && getUsuarioByUsername(usuario.getUsername()) == null) {
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    @Override
    public Usuario getUsuarioByUsername(String nombre_usuario) {
        return usuarioRepository.findUsuariosByUsername(nombre_usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getId() == 0) {
            return null;
        }
        return usuarioRepository.save(usuario);
    }

    @Override
    public void eliminarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> findUsuariosById(int id) {
        return Optional.ofNullable(usuarioRepository.findUsuariosById(id));
    }

    @Override
    public Optional<Usuario> findUsuariosByUsername(String nombre_usuario) {
        return Optional.ofNullable(usuarioRepository.findUsuariosByUsername(nombre_usuario));
    }

    @Override
    public Optional<Usuario> findUsuariosByEmail(String correo_electronico) {
        return Optional.ofNullable(usuarioRepository.findUsuarioByEmail(correo_electronico));
    }
}
