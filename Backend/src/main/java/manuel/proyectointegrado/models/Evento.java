package manuel.proyectointegrado.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import lombok.*;


import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "eventos")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
/*----------------Poner siempre por si acaso (si uso la dependencia lombok)--------------------*/
@Builder
@Data
/*-----------------------------------------*/

public class Evento {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idEvento")
    private int idEvento;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "fecha")
    private String fecha;

    @Column(name = "hora")
    private String hora;

    @Column(name = "lugar")
    private String lugar;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "invitados")
    private String invitados;

    @Digits(integer = 10, fraction = 2)
    @Column(name = "precio", precision = 12, scale = 2)
    private double precio;

    @ManyToOne
    @JoinColumn(name = "creador_id")
    @JsonIgnore
    private Usuario usuario;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Evento evento)) return false;
        return idEvento == evento.idEvento && Double.compare(precio, evento.precio) == 0 && Objects.equals(titulo, evento.titulo) && Objects.equals(fecha, evento.fecha) && Objects.equals(hora, evento.hora) && Objects.equals(lugar, evento.lugar) && Objects.equals(descripcion, evento.descripcion) && Objects.equals(invitados, evento.invitados) && Objects.equals(usuario, evento.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idEvento, titulo, fecha, hora, lugar, descripcion, invitados, precio, usuario);
    }
}
