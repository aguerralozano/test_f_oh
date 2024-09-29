package com.aguerra.test_financiera_oh.controller;

import java.util.List;

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

import com.aguerra.test_financiera_oh.dto.EstudianteDto;
import com.aguerra.test_financiera_oh.exception.BadRequestException;
import com.aguerra.test_financiera_oh.service.EstudianteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import jakarta.validation.Valid;

/**
 * Clase que implementa el controladores REST Estudiante
 * 
 * @author Angel Guerra
 */

@RestController
@Tag(name = "Estudiante", description = "Estudiante API")
@RequestMapping("/api/v1/estudiante")
public class EstudianteController {

    private static final Logger logger = LoggerFactory.getLogger(EstudianteController.class);
	
	@Autowired
	EstudianteService estudianteService;

    @GetMapping()
    @Operation(summary = "Obtener todos los estudiantes", description = "Devuelve una lista de todos los estudiantes")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", 
        description = "Lista de estudiantes",
        content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = EstudianteDto.class)))),
        @ApiResponse(responseCode = "204", description = "No se encontraron estudiantes")
    })
    public ResponseEntity<List<EstudianteDto>> obtenerEstudiantes() {
    	
        List<EstudianteDto> estudiantes = null;

        estudiantes = estudianteService.obtenerEstudiantes();
        logger.info("estudiantes: " + estudiantes);
    	
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteDto> obtenerEstudiantes(@PathVariable Integer id) {
    	
        EstudianteDto estudiante = null;

        estudiante = estudianteService.obtenerEstudiantePorId(id);
        logger.info("estudiantes: " + estudiante);
    	
        return ResponseEntity.ok(estudiante);
    }

    @PostMapping()
    public ResponseEntity<EstudianteDto> crearEstudiante(@Valid @RequestBody EstudianteDto estudianteDto, BindingResult result) {
        EstudianteDto newEstudiante = null;

        if (result.hasErrors()) {
            throw new BadRequestException(result.getFieldError().getDefaultMessage());
        }

        newEstudiante = estudianteService.insertarEstudiante(estudianteDto);
        logger.info("newEstudiante: " + newEstudiante);

        return ResponseEntity.status(HttpStatus.CREATED).body(newEstudiante);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteDto> actualizarEstudiante(@PathVariable Integer id, @Valid @RequestBody EstudianteDto estudianteDto, BindingResult result) {
        EstudianteDto updEstudiante = null;

        if (result.hasErrors()) {
            throw new BadRequestException(result.getFieldError().getDefaultMessage());
        }

        updEstudiante = estudianteService.actualizarEstudiante(id, estudianteDto);
        logger.info("updEstudiante: " + updEstudiante);

        return ResponseEntity.ok().body(updEstudiante);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEstudiante(@PathVariable Integer id) {

        logger.info("delete idEstudiante:" + id);
        estudianteService.eliminarEstudiante(id);
        
        return ResponseEntity.noContent().build();  // 204 No Content
    }

}
