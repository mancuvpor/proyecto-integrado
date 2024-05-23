package manuel.proyectointegrado.controllers;

import lombok.RequiredArgsConstructor;
import manuel.proyectointegrado.models.Evento;
import manuel.proyectointegrado.services.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v0/eventos")
public class EventoController {

    private final EventoService eventoService;

    @GetMapping
    public ResponseEntity<List<Evento>> listar() {
        return ResponseEntity.ok(eventoService.getAllEventos());
    }

}
