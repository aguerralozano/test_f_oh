package com.aguerra.test_financiera_oh.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.aguerra.test_financiera_oh.exception.BadRequestException;
import com.aguerra.test_financiera_oh.exception.ConflictException;
import com.aguerra.test_financiera_oh.exception.NoContentException;
import com.aguerra.test_financiera_oh.exception.NotFoundException;
import com.aguerra.test_financiera_oh.dto.Error;

/**
 * Clase para capturar y manejar excepciones lanzadas por los controladores REST
 * 
 * @author Angel Guerra
 */
@RestControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Error> notFoundException(Exception ex) {

        return getError(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoContentException.class)
    public ResponseEntity<Error> noContentException(Exception ex) {

        return getError(ex, HttpStatus.NO_CONTENT);

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Error> badRequestException(Exception ex) {

        return getError(ex, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Error> conflictException(Exception ex) {

        return getError(ex, HttpStatus.CONFLICT);

    }

    private ResponseEntity<Error> getError(Exception ex, HttpStatus status) {
        Error error = new Error();
        error.setDate(new Date());
        error.setMessage(ex.getMessage());
        error.setStatus(status.value());

        return ResponseEntity.status(error.getStatus()).body(error);
    }

}
