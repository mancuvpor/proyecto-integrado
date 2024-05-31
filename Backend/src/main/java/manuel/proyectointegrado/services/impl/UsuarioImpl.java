package manuel.proyectointegrado.services.impl;

import io.micrometer.common.util.StringUtils;
import manuel.proyectointegrado.config.jwt.UserAuthProvider;
import manuel.proyectointegrado.dto.CredentialsDTO;
import manuel.proyectointegrado.dto.EventoDTO;
import manuel.proyectointegrado.dto.SignUpDTO;
import manuel.proyectointegrado.dto.UsuarioDTO;
import manuel.proyectointegrado.exceptions.AppException;
import manuel.proyectointegrado.mappers.UsuarioMapper;
import manuel.proyectointegrado.models.Evento;
import manuel.proyectointegrado.models.Usuario;
import manuel.proyectointegrado.repositories.UsuarioRepository;
import manuel.proyectointegrado.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsuarioImpl implements UsuarioService {


    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    private final UsuarioMapper usuarioMapper;

    private final UserAuthProvider userAuthProvider;


    public UsuarioImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, UsuarioMapper usuarioMapper, UserAuthProvider userAuthProvider) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
        this.userAuthProvider = userAuthProvider;
    }


    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }


    @Override
    public Usuario createUsuario(UsuarioDTO usuarioDTO, String token) {

        if (!Objects.equals(userAuthProvider.extraerTipoUsuariodelToken(token), "admin")) {
            throw new AppException("No eres admin ", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(usuarioDTO.getUsername()) || StringUtils.isBlank(usuarioDTO.getNombre()) || StringUtils.isBlank(usuarioDTO.getApellidos()) || StringUtils.isBlank(usuarioDTO.getSexo()) || StringUtils.isBlank(usuarioDTO.getEmail()) || StringUtils.isBlank(usuarioDTO.getContrasena()) || StringUtils.isBlank(usuarioDTO.getTipo_usuario())) {
            throw new AppException("Username, nombre, apellidos, sexo, email, contraseña y tipo de usuario son obligatorios ", HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> optionalUserByUsername = usuarioRepository.findUsuariosByUsername(usuarioDTO.getUsername());
        if (optionalUserByUsername.isPresent()) {
            throw new AppException("Nombre de usuario existente ", HttpStatus.BAD_REQUEST);
        }

        Optional<Usuario> optionalUserByEmail = usuarioRepository.findUsuarioByEmail(usuarioDTO.getEmail());
        if (optionalUserByEmail.isPresent()) {
            throw new AppException("Email existente ", HttpStatus.BAD_REQUEST);
        }

        if (!isValidEmail(usuarioDTO.getEmail())) {
            throw new AppException("Email no válido ", HttpStatus.BAD_REQUEST);
        }

        if (!usuarioDTO.getTipo_usuario().equals("ofertante") && !usuarioDTO.getTipo_usuario().equals("consumidor") && !usuarioDTO.getTipo_usuario().equals("admin")) {
            throw new AppException("Tipo de usuario debe de ser consumidor, admin u ofertante ", HttpStatus.BAD_REQUEST);
        }

        Usuario nuevoUsuario = usuarioMapper.convertirAUsuario(usuarioDTO);
        nuevoUsuario.setContrasena(passwordEncoder.encode(CharBuffer.wrap(usuarioDTO.getContrasena())));
        return usuarioRepository.save(nuevoUsuario);
    }


    @Override
    public UsuarioDTO findUsuariosByUsername(String username) {
        Usuario user = usuarioRepository.findUsuariosByUsername(username)
                .orElseThrow(() -> new AppException("Usuario no encontrado", HttpStatus.NOT_FOUND));
        return usuarioMapper.toUserDto(user);
    }

    @Override
    public Optional<Usuario> getUsuarioById(int id) {
        return usuarioRepository.findById(id);
    }


    @Override
    public Usuario updateUsuario(int id, UsuarioDTO usuarioDTO, String token) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {

            if (!Objects.equals(userAuthProvider.extraerTipoUsuariodelToken(token), "admin")) {
                throw new AppException("No eres admin ", HttpStatus.BAD_REQUEST);
            }

            if (!Objects.equals(userAuthProvider.extraerTipoUsuariodelToken(token), "admin")) {
                if (!Objects.equals(userAuthProvider.extraerUsuariodelToken(token), usuarioDTO.getUsername())) {
                    throw new AppException("No puedes editar un usuario que no eres tu ", HttpStatus.BAD_REQUEST);
                }
            }

            if (StringUtils.isBlank(usuarioDTO.getUsername()) || StringUtils.isBlank(usuarioDTO.getNombre()) || StringUtils.isBlank(usuarioDTO.getApellidos()) || StringUtils.isBlank(usuarioDTO.getSexo()) || StringUtils.isBlank(usuarioDTO.getEmail()) || StringUtils.isBlank(usuarioDTO.getContrasena()) || StringUtils.isBlank(usuarioDTO.getTipo_usuario())) {
                throw new AppException("Username, nombre, apellidos, sexo, email, contraseña y tipo de usuario son obligatorios ", HttpStatus.BAD_REQUEST);
            }

            Optional<Usuario> optionalUserByUsername = usuarioRepository.findUsuariosByUsername(usuarioDTO.getUsername());
            if (optionalUserByUsername.isPresent()) {
                throw new AppException("Nombre de usuario existente ", HttpStatus.BAD_REQUEST);
            }

            Optional<Usuario> optionalUserByEmail = usuarioRepository.findUsuarioByEmail(usuarioDTO.getEmail());
            if (optionalUserByEmail.isPresent()) {
                throw new AppException("Email existente ", HttpStatus.BAD_REQUEST);
            }

            if (!isValidEmail(usuarioDTO.getEmail())) {
                throw new AppException("Email no válido ", HttpStatus.BAD_REQUEST);
            }

            if (!usuarioDTO.getTipo_usuario().equals("ofertante") && !usuarioDTO.getTipo_usuario().equals("consumidor") && !usuarioDTO.getTipo_usuario().equals("admin")) {
                throw new AppException("Tipo de usuario debe de ser consumidor, admin u ofertante ", HttpStatus.BAD_REQUEST);
            }

            Usuario usuarioActualizado = usuarioMapper.convertirAUsuario(usuarioDTO);
            usuarioActualizado.setId(id);
            return usuarioRepository.save(usuarioActualizado);
        }
        return null;
    }

    @Override
    public String deleteUsuario(int id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            return "Usuario con id " + id + " borrado con éxito";
        }
        return "Usuario con id " + id + " no encontrado";
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

        if (!isValidEmail(signUpDTO.email())) {
            throw new AppException("Email no válido ", HttpStatus.BAD_REQUEST);
        }

        if (!signUpDTO.tipo_usuario().equals("ofertante") && !signUpDTO.tipo_usuario().equals("consumidor")) {
            throw new AppException("Tipo de usuario debe de ser consumidor u ofertante ", HttpStatus.BAD_REQUEST);
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
