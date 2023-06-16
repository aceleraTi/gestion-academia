package com.acelerati.gestionacademia.infraestructure.rest.mapper;

import com.acelerati.gestionacademia.domain.Materia;
import com.acelerati.gestionacademia.infraestructure.rest.dto.MateriaGetDto;
import com.acelerati.gestionacademia.infraestructure.rest.dto.MateriaPostDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")

public interface MateriaDtoMapper {


    MateriaPostDto toMateriaPostDto(Materia materia);


    MateriaGetDto toMateriaGetDto(Materia materia);

    List<MateriaGetDto> toMateriaGetDtos(List<Materia> materias);

    @InheritInverseConfiguration
    @Mapping(target = "materiaPrerequisito", ignore = true)
    @Mapping(target = "id", ignore = true)
    Materia toMateria(MateriaPostDto materiaPostDto);


}
