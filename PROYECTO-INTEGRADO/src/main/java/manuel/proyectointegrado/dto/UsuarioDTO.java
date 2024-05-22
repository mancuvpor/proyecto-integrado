package manuel.proyectointegrado.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
//Las clases DTO son "copias" de las clases MODELOS, pero son manejables (por ejemplo, pueden haber atributos que no hay en los modelos)
public class UsuarioDTO {

    private int id;
    private String username;
    private String nombre;
    private String apellidos;

    private enum sexo {
        H, M
    }

    private String email;
    private String contrasena;

    private enum tipo_usuario {
        admin, consumidor, ofertante
    }

    private String token;
}
