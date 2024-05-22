package manuel.proyectointegrado.dto;

//DTO del registro
public record SignUpDTO(String nombre_usuario, String nombre, String apellidos, String sexo, String correo_electronico, char[] contrasena, String tipo_usuario) {
}