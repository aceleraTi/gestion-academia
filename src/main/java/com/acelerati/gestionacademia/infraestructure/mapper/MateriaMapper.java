package com.acelerati.gestionacademia.infraestructure.mapper;

import com.acelerati.gestionacademia.domain.Materia;
import com.acelerati.gestionacademia.infraestructure.entity.MateriaEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface MateriaMapper {


    Materia toMateria(MateriaEntity materia);

}
