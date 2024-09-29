package com.aguerra.test_financiera_oh.exception;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Asigna el código 204 No Content
 * 
 * @author Angel Guerra
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException {

    public NoContentException(String message) {
        super(message);  // Pasa el mensaje a RuntimeException
    }

}
