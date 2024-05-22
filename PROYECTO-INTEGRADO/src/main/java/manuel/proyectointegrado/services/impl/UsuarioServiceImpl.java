package manuel.proyectointegrado.services.impl;

import io.micrometer.common.util.StringUtils;
import manuel.proyectointegrado.dto.CredentialsDTO;
import manuel.proyectointegrado.dto.SignUpDTO;
import manuel.proyectointegrado.dto.UsuarioDTO;
import manuel.proyectointegrado.exceptions.AppException;
import manuel.proyectointegrado.mappers.UsuarioMapper;
import manuel.proyectointegrado.models.Usuario;
import manuel.proyectointegrado.repositories.UsuarioRepository;
import manuel.proyectointegrado.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final UsuarioMapper usuarioMapper;


    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }


    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }


    @Override
    public UsuarioDTO findUsuariosByUsername(String username) {
        Usuario user = usuarioRepository.findUsuariosByUsername(username)
                .orElseThrow(() -> new AppException("Usuario no encontrado", HttpStatus.NOT_FOUND));
        return usuarioMapper.toUserDto(user);
    }

    @Override
    public UsuarioDTO login(CredentialsDTO credentialsDTO) {

        Usuario user = usuarioRepository.findUsuariosByUsername(credentialsDTO.nombre_usuario())
                .orElseThrow(() -> new AppException("Usuario no encontrado", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.contrasena()),
                user.getContrasena())) {
            return usuarioMapper.toUserDto(user);
        }

        throw new AppException("Contraseña inválida", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UsuarioDTO register(SignUpDTO signUpDTO) {
        if (StringUtils.isBlank(signUpDTO.nombre_usuario()) || StringUtils.isBlank(signUpDTO.nombre()) || StringUtils.isBlank(signUpDTO.apellidos()) || StringUtils.isBlank(signUpDTO.sexo()) || StringUtils.isBlank(signUpDTO.correo_electronico()) || StringUtils.isBlank(Arrays.toString(signUpDTO.contrasena())) || StringUtils.isBlank(signUpDTO.tipo_usuario())) {
            throw new AppException("Nombre de usuario, nombre, apellidos, sexo, correo electrónico, contraseña y tipo de usuario son obligatorios ", HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> optionalUserByUsername = usuarioRepository.findUsuariosByUsername(signUpDTO.nombre_usuario());
        if (optionalUserByUsername.isPresent()) {
            throw new AppException("Nombre de usuario existente ", HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> optionalUserByEmail = usuarioRepository.findUsuarioByEmail(signUpDTO.correo_electronico());
        if (optionalUserByEmail.isPresent()) {
            throw new AppException("Correo electronico existente ", HttpStatus.BAD_REQUEST);
        }

        Usuario user = usuarioMapper.signUpToUser(signUpDTO);
        user.setContrasena(passwordEncoder.encode(CharBuffer.wrap(signUpDTO.contrasena())));

        Usuario savedUser = usuarioRepository.save(user);

        return usuarioMapper.toUserDto(savedUser);
    }
}
