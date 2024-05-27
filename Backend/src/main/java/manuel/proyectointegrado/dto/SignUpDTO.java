package manuel.proyectointegrado.dto;

//DTO del registro
public record SignUpDTO(String username, String nombre, String apellidos, String sexo, String email, char[] contrasena, String tipo_usuario) {
}