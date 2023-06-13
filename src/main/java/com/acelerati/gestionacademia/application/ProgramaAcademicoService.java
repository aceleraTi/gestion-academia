package com.acelerati.gestionacademia.application;

import com.acelerati.gestionacademia.domain.ProgramaAcademico;
import com.acelerati.gestionacademia.infraestructure.inputport.ProgramaAcademicoInputPort;
import com.acelerati.gestionacademia.infraestructure.outputport.ProgramaAcademicoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramaAcademicoService implements ProgramaAcademicoInputPort {

    private final ProgramaAcademicoRepositoryPort programaAcademicoRepositoryPort;

    @Override
    public ProgramaAcademico save(ProgramaAcademico programaAcademico) {
        return this.programaAcademicoRepositoryPort.save(programaAcademico);
    }
}
