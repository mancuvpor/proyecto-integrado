package manuel.proyectointegrado.controllers;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import manuel.proyectointegrado.dto.UsuarioDTO;
import manuel.proyectointegrado.models.Usuario;
import manuel.proyectointegrado.services.UsuarioService;
import manuel.proyectointegrado.utils.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v0/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.getAllUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> buscarUsuarioPorId(@PathVariable int id) {
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

    @PostMapping
    public ResponseEntity<Usuario> insertarUsuario(@RequestBody UsuarioDTO usuario, HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        Usuario nuevoUsuario = usuarioService.createUsuario(usuario, token);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable int id, @RequestBody UsuarioDTO usuario, HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        return ResponseEntity.ok(usuarioService.updateUsuario(id, usuario, token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> eliminarUsuario(@PathVariable int id, HttpServletRequest request) {
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        String mensaje = usuarioService.deleteUsuario(id, token);
        return ResponseEntity.ok(new ApiResponse(mensaje));
    }
}