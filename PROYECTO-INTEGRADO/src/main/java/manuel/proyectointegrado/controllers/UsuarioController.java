package manuel.proyectointegrado.controllers;


//@Controller
//@RequestMapping("/usuarios")
//public class UsuarioController {
//
//    static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
//
//    @Autowired
//    UsuarioServiceImpl usuarioService;
//
//
//    @GetMapping("/")
//    public String usuarios(Model model) {
//        List<Usuario> usuarios = usuarioService.getAllUsuarios();
//        model.addAttribute("usuarios", usuarios);
//        return "usuarios";
//    }
//
//
//    @GetMapping("/delete")
//    public String usuariosBorrar(@RequestParam(required = false, name = "codigo") String codigo, Model model) {
//
//        logger.info("prueba");
//
//        Usuario usuarioEntity = usuarioService.findUsuariosById(Integer.parseInt(codigo)).get();
//        int usuarioID = usuarioEntity.getId();
//
////            usu.setDepartamento(null);
////            usuarioService.deleteUsuariosById(usuarioID);
//
//        return "redirect:/usuarios/?codigo=" + usuarioID;
//    }
//}
