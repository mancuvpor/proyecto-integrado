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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsuarioImpl implements UsuarioService {


    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final UsuarioMapper usuarioMapper;


    public UsuarioImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper) {
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

        Usuario user = usuarioRepository.findUsuariosByUsername(credentialsDTO.username())
                .orElseThrow(() -> new AppException("Usuario no encontrado", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDTO.contrasena()),
                user.getContrasena())) {
            return usuarioMapper.toUserDto(user);
        }

        throw new AppException("Contraseña inválida", HttpStatus.BAD_REQUEST);
    }

    @Override
    public UsuarioDTO register(SignUpDTO signUpDTO) {
        if (StringUtils.isBlank(signUpDTO.username()) || StringUtils.isBlank(signUpDTO.nombre()) || StringUtils.isBlank(signUpDTO.apellidos()) || StringUtils.isBlank(signUpDTO.sexo()) || StringUtils.isBlank(signUpDTO.email()) || StringUtils.isBlank(Arrays.toString(signUpDTO.contrasena())) || StringUtils.isBlank(signUpDTO.tipo_usuario())) {
            throw new AppException("Username, nombre, apellidos, sexo, email, contraseña y tipo de usuario son obligatorios ", HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> optionalUserByUsername = usuarioRepository.findUsuariosByUsername(signUpDTO.username());
        if (optionalUserByUsername.isPresent()) {
            throw new AppException("Nombre de usuario existente ", HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> optionalUserByEmail = usuarioRepository.findUsuarioByEmail(signUpDTO.email());
        if (optionalUserByEmail.isPresent()) {
            throw new AppException("Email existente ", HttpStatus.BAD_REQUEST);
        }

        if (!isValidEmail(signUpDTO.email())){
            throw new AppException("Email no válido ", HttpStatus.BAD_REQUEST);
        }

        Usuario user = usuarioMapper.signUpToUser(signUpDTO);
        user.setContrasena(passwordEncoder.encode(CharBuffer.wrap(signUpDTO.contrasena())));

        Usuario savedUser = usuarioRepository.save(user);

        return usuarioMapper.toUserDto(savedUser);
    }

    public static boolean isValidEmail(String email) {
        String EMAIL_REGEX =
                "^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$";

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
