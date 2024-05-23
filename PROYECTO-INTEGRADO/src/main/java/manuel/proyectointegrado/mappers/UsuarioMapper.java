package manuel.proyectointegrado.mappers;

import manuel.proyectointegrado.dto.SignUpDTO;
import manuel.proyectointegrado.dto.UsuarioDTO;
import manuel.proyectointegrado.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

//Clase que mapea el DTO con el MODELO
@Mapper(componentModel = "spring")
public interface UsuarioMapper {


    UsuarioDTO toUserDto(Usuario user);

    @Mapping(target = "contrasena", ignore = true)
    Usuario signUpToUser(SignUpDTO signUpDto);

    Usuario toUser(UsuarioDTO userDto);

}