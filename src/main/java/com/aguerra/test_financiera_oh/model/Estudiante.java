package com.aguerra.test_financiera_oh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
//import java.math.BigDecimal;

/**
 * Esta clase representa una tabla en la base de datos
 * 
 * @author Angel Guerra
 */
@Entity
@Data
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 40)
    private String nombre;

    @Column(nullable = false, length = 40)
    private String apellido;

    @Column(nullable = false, length = 40, unique = true)
    private String email;

    @Column(nullable = false)
    private Integer creditos;

    @Column(nullable = false)
    private Integer semestre;

    @Column(nullable = false)
    private Integer promedio;
}
