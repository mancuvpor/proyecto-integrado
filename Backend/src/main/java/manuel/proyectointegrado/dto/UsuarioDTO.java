package manuel.proyectointegrado.dto;

import lombok.*;


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

    private String sexo;

    private String email;
    private String contrasena;

    private String tipo_usuario;

    private String token;
}
