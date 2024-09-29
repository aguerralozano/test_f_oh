package com.aguerra.test_financiera_oh.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

/**
 * Clase para exponer datos del Estudiante
 * 
 * @author Angel Guerra
 */
@Data
public class EstudianteDto {

    private Integer id;

    @Size(max = 40, message = "El nombre no puede tener más de 40 caracteres")
    @NotNull(message = "El campo nombre no puede ser nula")
    private String nombre;

    @Size(max = 40, message = "El apellido no puede tener más de 40 caracteres")
    @NotNull(message = "El campo apellido no puede ser nulo")
    private String apellido;

    @Size(max = 40, message = "El email no puede tener más de 40 caracteres")
    @NotNull(message = "El campo email no puede ser nulo")
    private String email;

    @NotNull(message = "El campo creditos no puede ser nulo")
    private Integer creditos;

    @NotNull(message = "El campo semestre no puede ser nulo")
    private Integer semestre;

    @NotNull(message = "El campo promedio no puede ser nulo")
    private Integer promedio;
}
