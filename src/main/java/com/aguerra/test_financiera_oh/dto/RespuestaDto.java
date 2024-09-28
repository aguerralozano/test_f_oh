package com.aguerra.test_financiera_oh.dto;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class RespuestaDto {

    private Boolean resultado;

    private HttpStatus status;

    private String mensaje;

    private EstudianteDto estudianteDto;

    public RespuestaDto() {
        this.resultado = true;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
        this.resultado = false;
    }
}
