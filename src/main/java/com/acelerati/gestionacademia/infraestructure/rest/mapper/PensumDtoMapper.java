package com.acelerati.gestionacademia.infraestructure.rest.mapper;


import com.acelerati.gestionacademia.domain.Pensum;
import com.acelerati.gestionacademia.infraestructure.rest.dto.PensumGetDto;
import com.acelerati.gestionacademia.infraestructure.rest.dto.PensumMateriaGetDto;
import com.acelerati.gestionacademia.infraestructure.rest.dto.PensumPostDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PensumDtoMapper {


    PensumPostDto toPensumPostDto(Pensum pensum);


    PensumGetDto toPensumGetDto(Pensum pensum);

    @InheritInverseConfiguration
    @Mapping(target = "materias", ignore = true)
    @Mapping(target = "id", ignore = true)
    Pensum toPensum(PensumPostDto pensumPostDto);
}
