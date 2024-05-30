package manuel.proyectointegrado.controllers;


import lombok.RequiredArgsConstructor;
import manuel.proyectointegrado.dto.UsuarioDTO;
import manuel.proyectointegrado.models.Usuario;
import manuel.proyectointegrado.services.UsuarioService;
import manuel.proyectointegrado.utils.ApiResponse;
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

//    @GetMapping("/{id}")
//    public ResponseEntity<Optional<Usuario>> buscarUsuarioPorId(@PathVariable int id) {
//        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
//    }
//
//    @PostMapping
//    public ResponseEntity<Usuario> insertarUsuario(@RequestBody UsuarioDTO usuario) {
//        Usuario nuevoEvento = usuarioService.createUsuario(usuario);
//        return ResponseEntity.ok(nuevoEvento);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Usuario> actualizarEvento(@PathVariable int id, @RequestBody UsuarioDTO usuario) {
//        return ResponseEntity.ok(usuarioService.updateUsuario(id, usuario));
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse> eliminarUsuario(@PathVariable int id) {
//        String mensaje = usuarioService.deleteUsuario(id);
//        return ResponseEntity.ok(new ApiResponse(mensaje));
//    }
}