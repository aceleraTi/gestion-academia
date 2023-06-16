package com.acelerati.gestionacademia.infraestructure.rest.mapper;

import com.acelerati.gestionacademia.domain.ProgramaAcademico;
import com.acelerati.gestionacademia.infraestructure.rest.dto.ProgramaAcademicoPostDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgramaAcademicoDtoMapper {

    ProgramaAcademicoPostDto toProgramaAcademicoPostDto(ProgramaAcademico programaAcademico);


//    PensumGetDto toPensumGetDto(Pensum pensum);

    @InheritInverseConfiguration
    @Mapping(target = "pensums", ignore = true)
    @Mapping(target = "id", ignore = true)
    ProgramaAcademico toProgramaAcademico(ProgramaAcademicoPostDto programaAcademicoPostDto);
}
