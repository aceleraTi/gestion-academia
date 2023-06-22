package com.acelerati.gestionacademia.infraestructure.outputport;

import com.acelerati.gestionacademia.domain.Materia;

import java.util.List;

public interface MateriaRepositoryPort {


    Materia crearMateria(Materia materia);

    Materia buscarId(Long id);

    Boolean existeId(Long id);

    List<Materia> materiasIdPensum(Long idPensum);

}
