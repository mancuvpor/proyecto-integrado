package manuel.proyectointegrado.services;

import manuel.proyectointegrado.dto.EventoDTO;
import manuel.proyectointegrado.models.Evento;

import java.util.List;
import java.util.Optional;

public interface EventoService {


    List<Evento> getAllEventos();

    Evento createEvento(EventoDTO evento);

    Optional<Evento> getEventoById(int id);

    Evento updateEvento(int id, EventoDTO evento);

    String deleteEvento(int id);

}
