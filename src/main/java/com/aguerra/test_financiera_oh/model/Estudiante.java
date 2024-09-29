package com.aguerra.test_financiera_oh.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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

    /*
    @Id
    @Column(precision = 38, scale = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private BigDecimal id;
    */

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estudiante_seq_gen")
    @SequenceGenerator(name = "estudiante_seq_gen", sequenceName = "TEST_GH.ESTUDIANTE_SEQ", allocationSize = 1)
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
