package com.acelerati.gestionacademia.application;

import com.acelerati.gestionacademia.domain.model.ProgramaAcademico;
import com.acelerati.gestionacademia.infraestructure.exception.NoExisteNivelEducativoException;
import com.acelerati.gestionacademia.infraestructure.inputport.NivelEducativoInputPort;
import com.acelerati.gestionacademia.infraestructure.inputport.ProgramaAcademicoInputPort;
import com.acelerati.gestionacademia.infraestructure.outputport.ProgramaAcademicoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProgramaAcademicoService implements ProgramaAcademicoInputPort {

    private static final String NO_EXISTE_NIVEL_EDUCATIVO = "no existe nivel educativo";

    private final ProgramaAcademicoRepositoryPort programaAcademicoRepositoryPort;

    private final NivelEducativoInputPort nivelEducativoInputPort;

    @Override
    public ProgramaAcademico crearProgramaAcademico(ProgramaAcademico programaAcademico) {
        if (!this.nivelEducativoInputPort.existsById(programaAcademico.getIdNivelEducativo())) {
            throw new NoExisteNivelEducativoException(NO_EXISTE_NIVEL_EDUCATIVO);
        }
        return this.programaAcademicoRepositoryPort.save(programaAcademico);
    }
}
