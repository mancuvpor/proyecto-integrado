package manuel.proyectointegrado.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class UsuarioDTO implements Serializable {

    private int id;
    private String nombre_usuario;
    private String nombre;
    private String apellidos;

    private enum sexo {
        H, M
    }

    private String correo_electronico;
    private String contrasena;

    private enum tipo_usuario {
        admin, consumidor, ofertante
    }

}
