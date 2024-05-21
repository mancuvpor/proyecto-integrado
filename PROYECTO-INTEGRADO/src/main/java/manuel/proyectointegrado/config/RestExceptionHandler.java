package manuel.proyectointegrado.config;

import manuel.proyectointegrado.exceptions.AppException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import manuel.proyectointegrado.dto.ErrorDTO;


//Manipulador de excepciones, lo que haces es devolver el mensaje de error a la app
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {AppException.class})
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleException(AppException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(new ErrorDTO(ex.getMessage()));
    }
}
