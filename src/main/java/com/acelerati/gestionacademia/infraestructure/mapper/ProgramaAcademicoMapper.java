package com.acelerati.gestionacademia.infraestructure.mapper;

import com.acelerati.gestionacademia.domain.ProgramaAcademico;
import com.acelerati.gestionacademia.infraestructure.entity.ProgramaAcademicoEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProgramaAcademicoMapper {


    ProgramaAcademico toProgramaAcademico(ProgramaAcademicoEntity programaAcademicoEntity);


    @InheritInverseConfiguration
    @Mapping(target = "nivelEducativoEntity", ignore = true)
    @Mapping(target = "pensumEntities", ignore = true)
    ProgramaAcademicoEntity toProgramaAcademicoEntity(ProgramaAcademico programaAcademico);
}

