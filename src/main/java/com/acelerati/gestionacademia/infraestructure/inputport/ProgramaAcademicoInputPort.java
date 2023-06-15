package com.acelerati.gestionacademia.infraestructure.inputport;

import com.acelerati.gestionacademia.domain.ProgramaAcademico;

public interface ProgramaAcademicoInputPort {

    ProgramaAcademico crearProgramaAcademico(ProgramaAcademico programaAcademico);

    Boolean existePorId(Long id);

    ProgramaAcademico buscarId(Long id);

    Boolean existeNombre(String nombre);
}

