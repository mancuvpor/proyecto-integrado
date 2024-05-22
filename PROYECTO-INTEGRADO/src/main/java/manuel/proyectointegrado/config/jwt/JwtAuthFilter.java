package manuel.proyectointegrado.config.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final UserAuthProvider userAuthenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Si la petición se hace a Login o Register no se comprueba el token, de lo contrario, si
        if (request.getServletPath().matches("/api/v0/auth/login|/api/v0/auth/register")) {
            filterChain.doFilter(request, response);
        } else {
            //Obtenemos el header AUTHORIZATION (recibirá como Bearer token)
            String header = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (header != null) {
                //authElements --> Array que contiene el Bearer y el token divididos por un espacio generado por la función SPLIT
                String[] authElements = header.split(" ");

                if (authElements.length == 2 && "Bearer".equals(authElements[0])) {
                    try {
                        SecurityContextHolder.getContext().setAuthentication(userAuthenticationProvider.validateToken(authElements[1]));
                    } catch (RuntimeException e) {
                        SecurityContextHolder.clearContext();
                        throw e;
                    }
                }
            }

            filterChain.doFilter(request, response);
        }
    }
}
