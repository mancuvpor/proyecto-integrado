package manuel.proyectointegrado.mappers;

import manuel.proyectointegrado.dto.EventoDTO;
import manuel.proyectointegrado.models.Evento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EventoMapper {

    Evento convertiraEvento(EventoDTO eventoDTO);

}
