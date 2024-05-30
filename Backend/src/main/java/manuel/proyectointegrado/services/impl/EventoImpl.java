package manuel.proyectointegrado.services.impl;

import manuel.proyectointegrado.dto.EventoDTO;
import manuel.proyectointegrado.exceptions.AppException;
import manuel.proyectointegrado.mappers.EventoMapper;
import manuel.proyectointegrado.models.Evento;
import manuel.proyectointegrado.models.Usuario;
import manuel.proyectointegrado.repositories.EventoRepository;
import manuel.proyectointegrado.repositories.UsuarioRepository;
import manuel.proyectointegrado.services.EventoService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
            if (!isValidPrecio(evento.precio())){
                throw new AppException("Precio no válido ", HttpStatus.BAD_REQUEST);
            }
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
        if (optionalEvento.isPresent()) {
            if (!isValidPrecio(evento.precio())){
                throw new AppException("Precio no válido ", HttpStatus.BAD_REQUEST);
            }
            Evento eventoActualizado = eventoMapper.convertiraEvento(evento);
            eventoActualizado.setIdEvento(id);
            eventoActualizado.setUsuario(optionalEvento.get().getUsuario());
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

    public static boolean isValidPrecio(double precio) {
        String PRECIO_REGEX =
                "^\\d{1,10}(\\.\\d{1,2})?$";

        String precioStr = String.valueOf(precio);
        Pattern pattern = Pattern.compile(PRECIO_REGEX);
        Matcher matcher = pattern.matcher(precioStr);
        return matcher.matches();
    }
}
