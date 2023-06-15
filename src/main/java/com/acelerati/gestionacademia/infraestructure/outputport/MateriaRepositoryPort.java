package com.acelerati.gestionacademia.infraestructure.outputport;

import com.acelerati.gestionacademia.domain.Materia;
import com.acelerati.gestionacademia.domain.ProgramaAcademico;

public interface MateriaRepositoryPort {

    Materia buscarId(Long id);

    Boolean existeId(Long id);

}
