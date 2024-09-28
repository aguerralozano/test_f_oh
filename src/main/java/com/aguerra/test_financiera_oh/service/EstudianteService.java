package com.aguerra.test_financiera_oh.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aguerra.test_financiera_oh.dto.EstudianteDto;
import com.aguerra.test_financiera_oh.dto.RespuestaDto;
import com.aguerra.test_financiera_oh.model.Estudiante;
import com.aguerra.test_financiera_oh.repository.EstudianteRepository;

/**
 * Esta es una clase implementa la lógica de negocio de Producto
 * 
 * @author Angel Guerra
 */
@Service
public class EstudianteService {

    @Autowired
    EstudianteRepository estudianteRepo;

    public List<Estudiante> obtenerEstudiantes() {
        List<Estudiante> estudiantes = null;

        estudiantes = estudianteRepo.findAll();

        return estudiantes;
    }

    public RespuestaDto insertarEstudiante(EstudianteDto estudianteDto) {
        RespuestaDto respuestaDto = new RespuestaDto();
        Estudiante newEstudiante = new Estudiante();

        this.MapEstudianteDtoToEstudiante(estudianteDto, newEstudiante);

        List<Estudiante> estudiantesResult = estudianteRepo.findByEmail(newEstudiante.getEmail());
        if(estudiantesResult.size() > 0){
            respuestaDto.setStatus(HttpStatus.CONFLICT);
            respuestaDto.setMensaje("Ya existe un estudiante registrado con ese email");
            return respuestaDto;
        }

        newEstudiante = estudianteRepo.save(newEstudiante);
        respuestaDto.setEstudianteDto(estudianteDto);

        return respuestaDto;
    }

    public RespuestaDto actualizarEstudiante(Integer idEstudiante, EstudianteDto estudianteDto){
        RespuestaDto respuestaDto = new RespuestaDto();

        Optional<Estudiante> estudianteOptional = estudianteRepo.findById(idEstudiante);
        if(!estudianteOptional.isPresent()){
            respuestaDto.setStatus(HttpStatus.NOT_FOUND);
            respuestaDto.setMensaje("Estudiante no existe");
            return respuestaDto;
        }

        Estudiante updEstudiante = estudianteOptional.get();
        this.MapEstudianteDtoToEstudiante(estudianteDto, updEstudiante);

        List<Estudiante> estudiantesResult = estudianteRepo.findByEmail(updEstudiante.getEmail());
        if(estudiantesResult.size() > 0){
            respuestaDto.setStatus(HttpStatus.CONFLICT);
            respuestaDto.setMensaje("Ya existe un estudiante registrado con ese email");
            return respuestaDto;
        }

        updEstudiante = estudianteRepo.save(updEstudiante);
        respuestaDto.setEstudianteDto(estudianteDto);

        return respuestaDto;
    }

    public RespuestaDto eliminarEstudiante(Integer idEstudiante){
        RespuestaDto respuestaDto = new RespuestaDto();
        Optional<Estudiante> estudiante = estudianteRepo.findById(idEstudiante);
        if(!estudiante.isPresent()){
            respuestaDto.setStatus(HttpStatus.NOT_FOUND);
            respuestaDto.setMensaje("Estudiante no existe");
            return respuestaDto;
        }

        estudianteRepo.delete(estudiante.get());
        return respuestaDto;
    }

    private void MapEstudianteDtoToEstudiante(EstudianteDto estudianteDto, Estudiante estudiante){
        estudiante.setNombre(estudianteDto.getNombre());
        estudiante.setApellido(estudianteDto.getApellido());
        estudiante.setEmail(estudianteDto.getEmail());
        estudiante.setCreditos(estudianteDto.getCreditos());
        estudiante.setSemestre(estudianteDto.getSemestre());
        estudiante.setPromedio(estudianteDto.getPromedio());
    }

}
