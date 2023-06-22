package com.acelerati.gestionacademia.infraestructure.outputport;

import com.acelerati.gestionacademia.domain.ProgramaAcademico;

import java.util.Optional;

public interface ProgramaAcademicoRepositoryPort {

    ProgramaAcademico crearProgramaAcademico(ProgramaAcademico programaAcademico);

    Boolean existeId(Long id);

    void eliminarId(Long idProgramaAcademico);

    ProgramaAcademico buscarId(Long id);

    Boolean existeNombre(String nombre);

    int actualizarDirector(Long idPrograma,
                           Long idDirector);

    Optional<ProgramaAcademico> buscarDirectorId(Long idDirector);

}

