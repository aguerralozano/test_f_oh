package com.aguerra.test_financiera_oh.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aguerra.test_financiera_oh.dto.EstudianteDto;
import com.aguerra.test_financiera_oh.exception.ConflictException;
import com.aguerra.test_financiera_oh.exception.NoContentException;
import com.aguerra.test_financiera_oh.exception.NotFoundException;
import com.aguerra.test_financiera_oh.mapper.EstudianteMapper;
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

    @Transactional(readOnly = true)
    public List<EstudianteDto> obtenerEstudiantes() {

        List<Estudiante> estudiantes = estudianteRepo.findAll();

        if (estudiantes == null || estudiantes.isEmpty()) {
            throw new NoContentException("No se encontraron estudiantes.");
        }

        // Usamos stream() y map() para convertir de Estudiante a EstudianteDto
        List<EstudianteDto> estudiantesDto = estudiantes.stream()
                .map(estudiante -> EstudianteMapper.toDTO(estudiante))
                .collect(Collectors.toList());

        return estudiantesDto;
    }

    @Transactional(readOnly = true)
    public EstudianteDto obtenerEstudiantePorId(Integer id) {

        Estudiante estudiante = estudianteRepo.findById(id)
            .orElseThrow(() -> new NotFoundException("Estudiante no encontrado con ID: " + id));

        return EstudianteMapper.toDTO(estudiante);
    }

    @Transactional()
    public EstudianteDto insertarEstudiante(EstudianteDto estudianteDto) {

        Estudiante newEstudiante = EstudianteMapper.toEntity(estudianteDto);

        this.validarEmail(newEstudiante);

        newEstudiante = estudianteRepo.save(newEstudiante);
        estudianteDto.setId(newEstudiante.getId());

        return estudianteDto;
    }

    @Transactional()
    public EstudianteDto actualizarEstudiante(Integer idEstudiante, EstudianteDto estudianteDto) {

        Optional<Estudiante> estudianteOptional = estudianteRepo.findById(idEstudiante);
        if (!estudianteOptional.isPresent()) {
            throw new NotFoundException("Estudiante no existe");
        }

        Estudiante updEstudiante = EstudianteMapper.toEntity(estudianteDto);
        updEstudiante.setId(estudianteOptional.get().getId());

        this.validarEmail(updEstudiante);

        updEstudiante = estudianteRepo.save(updEstudiante);
        estudianteDto.setId(idEstudiante);

        return estudianteDto;
    }

    @Transactional()
    public void eliminarEstudiante(Integer idEstudiante) {
        Optional<Estudiante> estudiante = estudianteRepo.findById(idEstudiante);
        if (!estudiante.isPresent()) {
            throw new NotFoundException("Estudiante no existe");
        }

        estudianteRepo.delete(estudiante.get());
    }

    private void validarEmail(Estudiante estudiante) {
        String msjError = "Ya existe un estudiante registrado con ese email";
        if(estudiante.getId() == null) {
            //validación para estudiantes nuevos
            List<Estudiante> estudiantesResult = estudianteRepo.findByEmail(estudiante.getEmail());
            if (estudiantesResult.size() > 0) {
                throw new ConflictException(msjError);
            }
        } else {
            //validación para actualizar datos de estudiante
            if(estudianteRepo.existsByEmailAndNotId(estudiante.getEmail(), estudiante.getId())){
                throw new ConflictException(msjError);
            }
        }

    }
}
