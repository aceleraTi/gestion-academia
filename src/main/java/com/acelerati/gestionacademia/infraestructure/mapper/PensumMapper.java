package com.acelerati.gestionacademia.infraestructure.mapper;

import com.acelerati.gestionacademia.domain.Pensum;
import com.acelerati.gestionacademia.infraestructure.entity.PensumEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PensumMapper {

    Pensum toPensum(PensumEntity pensumEntity);


    @InheritInverseConfiguration
    @Mapping(target = "programaAcademicoEntity", ignore = true)
    @Mapping(target = "materiaEntities", ignore = true)
    PensumEntity toPensumEntity(Pensum pensum);
}
