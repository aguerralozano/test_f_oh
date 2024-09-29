package com.aguerra.test_financiera_oh.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Asigna el código 409 Conflict
 * 
 * @author Angel Guerra
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    public ConflictException(String message) {
        super(message); // Pasa el mensaje a RuntimeException
    }
}
