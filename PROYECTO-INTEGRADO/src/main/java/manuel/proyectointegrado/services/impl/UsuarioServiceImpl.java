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
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return usuarioMapper.toUserDto(user);
    }

    @Override
    public UsuarioDTO login(CredentialsDTO credentialsDTO) {

        Usuario user = usuarioRepository.findUsuariosByUsername(credentialsDTO.username())
                .orElseThrow(() -> new AppException("Unknown User", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.password()),
                user.getContrasena())) {
            return usuarioMapper.toUserDto(user);
        }

        throw new AppException("Invalid Password", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UsuarioDTO register(SignUpDTO signUpDTO) {
        if (StringUtils.isBlank(signUpDTO.username()) || StringUtils.isBlank(Arrays.toString(signUpDTO.password())) || StringUtils.isBlank(signUpDTO.email()) || StringUtils.isBlank(signUpDTO.nombre()) || StringUtils.isBlank(signUpDTO.apellidos())) {
            throw new AppException("Username, password, email, name and surname are required", HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> optionalUserByUsername = usuarioRepository.findUsuariosByUsername(signUpDTO.username());
        if (optionalUserByUsername.isPresent()) {
            throw new AppException("Username already exists", HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> optionalUserByEmail = usuarioRepository.findUsuarioByEmail(signUpDTO.email());
        if (optionalUserByEmail.isPresent()) {
            throw new AppException("Email already exists", HttpStatus.BAD_REQUEST);
        }

        Usuario user = usuarioMapper.signUpToUser(signUpDTO);
        user.setContrasena(passwordEncoder.encode(CharBuffer.wrap(signUpDTO.password())));

        Usuario savedUser = usuarioRepository.save(user);

        return usuarioMapper.toUserDto(savedUser);
    }
}
