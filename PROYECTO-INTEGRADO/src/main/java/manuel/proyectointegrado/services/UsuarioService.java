package manuel.proyectointegrado.services;

import manuel.proyectointegrado.dto.CredentialsDTO;
import manuel.proyectointegrado.dto.SignUpDTO;
import manuel.proyectointegrado.dto.UsuarioDTO;
import manuel.proyectointegrado.models.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {


    List<Usuario> getAllUsuarios();

    UsuarioDTO login(CredentialsDTO credentialsDTO);

    UsuarioDTO register(SignUpDTO signUpDTO);

    UsuarioDTO findUsuariosByUsername(String nombre_usuario);


}
