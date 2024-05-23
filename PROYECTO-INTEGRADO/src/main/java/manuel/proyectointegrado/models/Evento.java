package manuel.proyectointegrado.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
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

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha")
    private Date fecha;

    @Temporal(TemporalType.TIME)
    @Column(name = "hora")
    private String hora;

    @Column(name = "lugar")
    private String lugar;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "invitados")
    private String invitados;

    @Column(name = "precio")
    private double precio;

    @ManyToOne
    @JoinColumn(name = "creadorId")
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
