package com.acelerati.gestionacademia.infraestructure.outputport;

import com.acelerati.gestionacademia.domain.ProgramaAcademico;

public interface ProgramaAcademicoRepositoryPort {

    ProgramaAcademico save(ProgramaAcademico programaAcademico);

    void deleteById(Long idProgramaAcademico);
}

