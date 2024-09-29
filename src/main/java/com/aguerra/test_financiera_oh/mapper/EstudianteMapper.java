package com.aguerra.test_financiera_oh.mapper;

import com.aguerra.test_financiera_oh.dto.EstudianteDto;
import com.aguerra.test_financiera_oh.model.Estudiante;

/**
 * Clase para convertir entidad y DTO o viceversa
 * @author Angel Guerra
 */
public class EstudianteMapper {

    // Mapea de DTO a Entity
    public static Estudiante toEntity(EstudianteDto dto) {
        Estudiante entity = new Estudiante();

        entity.setNombre(dto.getNombre());
        entity.setApellido(dto.getApellido());
        entity.setEmail(dto.getEmail());
        entity.setCreditos(dto.getCreditos());
        entity.setSemestre(dto.getSemestre());
        entity.setPromedio(dto.getPromedio());

        return entity;
    }

    // Mapea de Entity a DTO
    public static EstudianteDto toDTO(Estudiante entity) {
        EstudianteDto dto = new EstudianteDto();

        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setApellido(entity.getApellido());
        dto.setEmail(entity.getEmail());
        dto.setCreditos(entity.getCreditos());
        dto.setSemestre(entity.getSemestre());
        dto.setPromedio(entity.getPromedio());

        return dto;
    }
}
