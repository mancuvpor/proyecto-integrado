package manuel.proyectointegrado.config.jwt;

import manuel.proyectointegrado.dto.UsuarioDTO;
import manuel.proyectointegrado.exceptions.AppException;
import manuel.proyectointegrado.services.UsuarioService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;


@RequiredArgsConstructor
@Component
//Clase que contiene todas las funcionalidades de los tokens
public class UserAuthProvider {
    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }


    //Función que se encarga de crear el token (cada token es nuevo, indiferentemente que te registres o te logees)
    public String createToken(UsuarioDTO user) {
        Date fecha_hoy = new Date();

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(user.getUsername())
                .withIssuedAt(fecha_hoy)
                .withClaim("nombre", user.getNombre())
                .withClaim("apellidos", user.getApellidos())
                .withClaim("tipo_usuario", user.getTipo_usuario())
                .withClaim("id", user.getId())
                .sign(algorithm);
    }


    //Función que se encarga de verificar el token
    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        UsuarioDTO user = UsuarioDTO.builder()
                .username(decoded.getSubject())
                .nombre(decoded.getClaim("nombre").asString())
                .apellidos(decoded.getClaim("apellidos").asString())
                .tipo_usuario(decoded.getClaim("tipo_usuario").asString())
                .id(decoded.getClaim("id").asInt())
                .build();

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    public String extraerUsuariodelToken(String token) {
        try {
            String[] authElements = token.split(" ");

            if (authElements.length == 2
                    && "Bearer".equals(authElements[0])) {

                Algorithm algorithm = Algorithm.HMAC256(secretKey);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decoded = verifier.verify(authElements[1]);
                return decoded.getSubject();
            }

            return null;
        } catch (Exception e) {
            throw new AppException("Error al extraer el usuario del token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public String extraerTipoUsuariodelToken(String token) {
        try {
            String[] authElements = token.split(" ");

            if (authElements.length == 2
                    && "Bearer".equals(authElements[0])) {

                Algorithm algorithm = Algorithm.HMAC256(secretKey);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decoded = verifier.verify(authElements[1]);
                return decoded.getClaim("tipo_usuario").asString();
            }

            return null;
        } catch (Exception e) {
            throw new AppException("Error al extraer el tipo de usuario del token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public int extraerIDdelToken(String token) {
        try {
            String[] authElements = token.split(" ");

            if (authElements.length == 2
                    && "Bearer".equals(authElements[0])) {

                Algorithm algorithm = Algorithm.HMAC256(secretKey);
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decoded = verifier.verify(authElements[1]);
                return decoded.getClaim("id").asInt();
            }

            return 0;
        } catch (Exception e) {
            throw new AppException("Error al extraer el tipo de usuario del token", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
