package manuel.proyectointegrado.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;


//Excepción personalizada
@Getter
public class AppException extends RuntimeException {

    private final HttpStatus status;

    public AppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}

