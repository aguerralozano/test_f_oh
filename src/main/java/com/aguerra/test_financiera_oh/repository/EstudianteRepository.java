package com.aguerra.test_financiera_oh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.aguerra.test_financiera_oh.model.Estudiante;

/**
 * Clase para manejar la persistencia de datos de la entidad Estudiante
 * 
 * @author Angel Guerra
 */
public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {

    List<Estudiante> findByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END " +
           "FROM Estudiante e WHERE e.email = :email AND e.id <> :id")
    boolean existsByEmailAndNotId(@Param("email") String email, @Param("id") Integer id);
}
