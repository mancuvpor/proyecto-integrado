package manuel.proyectointegrado.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
/*----------------Poner siempre por si acaso (si uso la dependencia lombok)--------------------*/
@Builder
@Data
/*-----------------------------------------*/
public class Usuario {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "nombre_usuario")
    private String username;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "sexo")
    private String sexo;

    @Column(name = "correo_electronico")
    private String email;

    @Column(name = "contrasena")
    private String contrasena;

    @Column(name = "tipo_usuario")
    private String tipo_usuario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return id == usuario.id && Objects.equals(username, usuario.username) && Objects.equals(nombre, usuario.nombre) && Objects.equals(apellidos, usuario.apellidos) && Objects.equals(sexo, usuario.sexo) && Objects.equals(email, usuario.email) && Objects.equals(contrasena, usuario.contrasena) && Objects.equals(tipo_usuario, usuario.tipo_usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, nombre, apellidos, sexo, email, contrasena, tipo_usuario);
    }
}
