package com.aguerra.test_financiera_oh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aguerra.test_financiera_oh.model.Estudiante;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    List<Estudiante> findByEmail(String email);
}
