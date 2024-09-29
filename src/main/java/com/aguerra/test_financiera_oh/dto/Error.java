package com.aguerra.test_financiera_oh.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase Error usado para Errores Personalizados
 * 
 * @author Angel Guerra
 */
@Getter
@Setter
public class Error {

    private String message;
    private int status;
    private Date date;

}
