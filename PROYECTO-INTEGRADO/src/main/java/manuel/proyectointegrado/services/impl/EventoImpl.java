package manuel.proyectointegrado.services.impl;

import manuel.proyectointegrado.models.Evento;
import manuel.proyectointegrado.repositories.EventoRepository;
import manuel.proyectointegrado.services.EventoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoImpl implements EventoService {

    private final EventoRepository eventoRepository;

    public EventoImpl(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Override
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }
}
