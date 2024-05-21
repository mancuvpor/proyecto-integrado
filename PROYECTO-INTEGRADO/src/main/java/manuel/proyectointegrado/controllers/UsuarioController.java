package manuel.proyectointegrado.controllers;

import manuel.proyectointegrado.models.Usuario;
import manuel.proyectointegrado.services.impl.UsuarioServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    UsuarioServiceImpl usuarioService;


    @GetMapping("/")
    public String usuarios(Model model) {
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        model.addAttribute("usuarios", usuarios);
        return "usuarios";
    }


    @GetMapping("/delete")
    public String usuariosBorrar(@RequestParam(required = false, name = "codigo") String codigo, Model model) {

        logger.info("prueba");

        Usuario usuarioEntity = usuarioService.findUsuariosById(Integer.parseInt(codigo)).get();
        int usuarioID = usuarioEntity.getId();

//            usu.setDepartamento(null);
//            usuarioService.deleteUsuariosById(usuarioID);

        return "redirect:/usuarios/?codigo=" + usuarioID;
    }
}
