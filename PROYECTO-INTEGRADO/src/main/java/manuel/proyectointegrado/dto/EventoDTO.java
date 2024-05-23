package manuel.proyectointegrado.dto;

import java.util.Date;

public record EventoDTO(String titulo, Date fecha, String hora, String lugar, String descripcion,
                        String invitados, double precio) {
}
