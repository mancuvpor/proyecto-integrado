package manuel.proyectointegrado.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordConfig {

    /*
     * ESTABLECEMOS EL PASSWORD ENCODER. FUERZA 15 (de 4 a 31)
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(15);
    }
}
