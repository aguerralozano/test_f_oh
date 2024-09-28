package com.aguerra.test_financiera_oh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.aguerra.test_financiera_oh.dto.EstudianteDto;
import com.aguerra.test_financiera_oh.dto.RespuestaDto;
import com.aguerra.test_financiera_oh.model.Estudiante;
import com.aguerra.test_financiera_oh.service.EstudianteService;

import jakarta.validation.Valid;

/**
 * Clase que implementa el controladores REST Estudiante
 * 
 * @author Angel Guerra
 */

@RestController
//@Tag(name = "Estudiante", description = "Estudiante API")
@RequestMapping("/api/v1/estudiante")
public class EstudianteController {

    private static final Logger logger = LoggerFactory.getLogger(EstudianteController.class);
	
	@Autowired
	EstudianteService estudianteService;

    @GetMapping()
    public ResponseEntity<List<Estudiante>> obtenerEstudiantes() {
    	
        List<Estudiante> estudiantes = null;

        try{
           estudiantes = estudianteService.obtenerEstudiantes();
           logger.info("estudiantes: " + estudiantes);
        }
        catch (Exception ex) {
            logger.error("Error al invocar estudianteService.obtenerEstudiantes() -> ", ex);
            return ResponseEntity.internalServerError().build();
        }
    	
        return ResponseEntity.ok(estudiantes);
    }

    @PostMapping()
    public ResponseEntity<String> crearEstudiante(@Valid @RequestBody EstudianteDto estudianteDto, BindingResult result) {
        RespuestaDto respuestaDto = null;

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }

        try{
            logger.info("estudianteDto: " + estudianteDto);
            respuestaDto = estudianteService.insertarEstudiante(estudianteDto);
            logger.info("respuestaDto: " + respuestaDto);
            if(!respuestaDto.getResultado()){
                return ResponseEntity.status(respuestaDto.getStatus()).body(respuestaDto.getMensaje());
            }

         }
         catch (Exception ex) {
             logger.error("Error al invocar estudianteService.insertarEstudiante() -> ", ex);
             return ResponseEntity.internalServerError().build();
         }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarEstudiante(@PathVariable Integer id, @Valid @RequestBody EstudianteDto estudianteDto, BindingResult result) {
        RespuestaDto respuestaDto = null;

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }

        try{
            logger.info("estudianteDto: " + estudianteDto);
            respuestaDto = estudianteService.actualizarEstudiante(id, estudianteDto);
            logger.info("respuestaDto: " + respuestaDto);
            if(!respuestaDto.getResultado()){
                return ResponseEntity.status(respuestaDto.getStatus()).body(respuestaDto.getMensaje());
            }

         }
         catch (Exception ex) {
             logger.error("Error al invocar estudianteService.actualizarEstudiante() -> ", ex);
             return ResponseEntity.internalServerError().build();
         }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Integer id) {
        try{
            logger.info("idEstudiante:" + id);
            RespuestaDto respuestaDto = estudianteService.eliminarEstudiante(id);

            if(!respuestaDto.getResultado()){
                return ResponseEntity.status(respuestaDto.getStatus()).build();
            }
        }
        catch (Exception ex) {
            logger.error("Error al invocar estudianteService.eliminarEstudiante() -> ", ex);
            return ResponseEntity.internalServerError().build();
        }
        
        return ResponseEntity.noContent().build();  // 204 No Content
    }

}
