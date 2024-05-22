package manuel.proyectointegrado.mappers;

import manuel.proyectointegrado.dto.SignUpDTO;
import manuel.proyectointegrado.dto.UsuarioDTO;
import manuel.proyectointegrado.models.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO toUserDto(Usuario user);

    @Mapping(target = "password", ignore = true)
    Usuario signUpToUser(SignUpDTO signUpDto);

    Usuario toUser(UsuarioDTO userDto);
}