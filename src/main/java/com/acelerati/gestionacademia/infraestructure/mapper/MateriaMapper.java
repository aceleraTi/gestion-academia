package com.acelerati.gestionacademia.infraestructure.mapper;

import com.acelerati.gestionacademia.domain.Materia;
import com.acelerati.gestionacademia.infraestructure.entity.MateriaEntity;
import com.acelerati.gestionacademia.infraestructure.entity.PensumEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.List;

@Mapper(componentModel = "spring")
public interface MateriaMapper {


    @Mapping(target = "materiaPrerequisito", source = "materiaEntityPrerequisito")
    Materia toMateria(MateriaEntity materia);


    @Mapping(target = "pensumEntity", ignore = true)
    List<Materia> toMaterias(List<MateriaEntity> materiaEntities);

    @InheritInverseConfiguration
    @Mapping(target = "pensumEntity", ignore = true)
    @Mapping(target = "materiaEntityPrerequisito", ignore = true)
    MateriaEntity toMateriaEntity(Materia materia);

}
