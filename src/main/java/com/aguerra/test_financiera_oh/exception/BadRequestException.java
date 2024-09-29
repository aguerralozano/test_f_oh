package com.aguerra.test_financiera_oh.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

/**
 * Asigna el código 400 Bad Request
 * 
 * @author Angel Guerra
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException(String message) {
        super(message); // Pasa el mensaje a RuntimeException
    }

}
