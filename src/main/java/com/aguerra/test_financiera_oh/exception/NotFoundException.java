package com.aguerra.test_financiera_oh.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Define el código 404 Not Found
 * 
 * @author Angel Guerra
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);  // Pasa el mensaje a RuntimeException
    }

}
