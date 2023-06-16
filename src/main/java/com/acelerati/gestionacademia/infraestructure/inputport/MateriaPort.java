package com.acelerati.gestionacademia.infraestructure.inputport;

import com.acelerati.gestionacademia.domain.Materia;

import java.util.List;

public interface MateriaPort {


    Materia crearMateria(Materia materia);

    Materia obtenerMateria(Long id);

    Boolean existeMateria(Long id);

    List<Materia> materiasIdPensum(Long idPensum);

}
