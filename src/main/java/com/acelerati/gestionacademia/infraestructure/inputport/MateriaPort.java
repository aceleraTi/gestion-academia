package com.acelerati.gestionacademia.infraestructure.inputport;

import com.acelerati.gestionacademia.domain.Materia;
import com.acelerati.gestionacademia.domain.Pensum;

public interface MateriaPort {
    Materia obtenerMateria(Long id);

    Boolean existeMateria(Long id);

}
