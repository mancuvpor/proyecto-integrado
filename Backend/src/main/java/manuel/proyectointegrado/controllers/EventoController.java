package manuel.proyectointegrado.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import manuel.proyectointegrado.dto.EventoDTO;
import manuel.proyectointegrado.models.Evento;
import manuel.proyectointegrado.services.EventoService;
import manuel.proyectointegrado.utils.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v0/eventos")
public class EventoController {

    private final EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> listarEventos() {
        return ResponseEntity.ok(eventoService.getAllEventos());
    }

    @GetMapping("/personales")
    public ResponseEntity<List<Evento>> getEventosPersonales(HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        return ResponseEntity.ok(eventoService.getAllEventosPersonales(token));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Evento>> buscarEventoPorId(@PathVariable int id) {
        return ResponseEntity.ok(eventoService.getEventoById(id));
    }

    @PostMapping
    public ResponseEntity<Evento> insertarEvento(@RequestBody EventoDTO evento) {
        Evento nuevoEvento = eventoService.createEvento(evento);
        return ResponseEntity.ok(nuevoEvento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizarEvento(@PathVariable int id, @RequestBody EventoDTO evento) {
        return ResponseEntity.ok(eventoService.updateEvento(id, evento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarEvento(@PathVariable int id) {
        String mensaje = eventoService.deleteEvento(id);
        return ResponseEntity.ok(new ApiResponse(mensaje));
    }
    
}
