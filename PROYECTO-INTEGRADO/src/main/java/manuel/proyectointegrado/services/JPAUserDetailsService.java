package manuel.proyectointegrado.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import manuel.proyectointegrado.models.JPAUserDetails;
import manuel.proyectointegrado.models.Usuario;
import manuel.proyectointegrado.repositories.UsuarioRepository;

@Service
public class JPAUserDetailsService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String nombre_usuario) throws UsernameNotFoundException {

        // Obtengo el usuario
        Usuario user = usuarioRepository.findUsuariosByUsername(nombre_usuario);

        // Si el usuario no existe debo devolver una excepción
        if (user == null) {
            throw new UsernameNotFoundException("Not found:" + nombre_usuario);
        }

        // Adapto la información del usuario al UserDetails que es lo que debe devolver el método
        JPAUserDetails userDetails = new JPAUserDetails(user);
        return userDetails;
    }
}
