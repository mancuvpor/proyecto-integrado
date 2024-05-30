package manuel.proyectointegrado.dto;

import manuel.proyectointegrado.models.Usuario;

import java.util.Date;

public record EventoDTO(String titulo, String fecha, String hora, String lugar, String descripcion,
                        String invitados, double precio, String telefono, Integer creador_id) {
}

