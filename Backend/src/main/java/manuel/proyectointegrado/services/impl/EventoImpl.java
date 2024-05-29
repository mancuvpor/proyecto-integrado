package manuel.proyectointegrado.services.impl;

import manuel.proyectointegrado.dto.EventoDTO;
import manuel.proyectointegrado.mappers.EventoMapper;
import manuel.proyectointegrado.models.Evento;
import manuel.proyectointegrado.models.Usuario;
import manuel.proyectointegrado.repositories.EventoRepository;
import manuel.proyectointegrado.repositories.UsuarioRepository;
import manuel.proyectointegrado.services.EventoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoImpl implements EventoService {

    private final EventoRepository eventoRepository;

    private final EventoMapper eventoMapper;
    private final UsuarioRepository usuarioRepository;

    public EventoImpl(EventoRepository eventoRepository, EventoMapper eventoMapper, UsuarioRepository usuarioRepository) {
        this.eventoRepository = eventoRepository;
        this.eventoMapper = eventoMapper;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Evento> getAllEventos() {
        return eventoRepository.findAll();
    }

    @Override
    public Evento createEvento(EventoDTO evento) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(evento.creador_id());
        if (optionalUsuario.isPresent()) {
            Usuario usuarioExistente = optionalUsuario.get();
            Evento nuevoEvento = eventoMapper.convertiraEvento(evento);
            nuevoEvento.setUsuario(usuarioExistente);
            return eventoRepository.save(nuevoEvento);
        }
        return null;
    }

    @Override
    public Optional<Evento> getEventoById(int id) {
        return eventoRepository.findById(id);
    }

    @Override
    public Evento updateEvento(int id, EventoDTO evento) {
        Optional<Evento> optionalEvento = eventoRepository.findById(id);
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(evento.creador_id());
        if (optionalEvento.isPresent() && optionalUsuario.isPresent()) {
            Usuario usuarioExistente = optionalUsuario.get();
            Evento eventoActualizado = eventoMapper.convertiraEvento(evento);
            eventoActualizado.setUsuario(usuarioExistente);
            eventoActualizado.setIdEvento(id);
            return eventoRepository.save(eventoActualizado);
        }
        return null;
    }

    @Override
    public String deleteEvento(int id) {
        if (eventoRepository.existsById(id)) {
            eventoRepository.deleteById(id);
            return "Evento con id " + id + " borrado con éxito";
        }
        return "Evento con id " + id + " no encontrado";
    }
}