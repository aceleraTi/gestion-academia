package com.acelerati.gestionacademia.application;

import com.acelerati.gestionacademia.domain.ProgramaAcademico;
import com.acelerati.gestionacademia.infraestructure.exception.BadRequestException;
import com.acelerati.gestionacademia.infraestructure.exception.NotFoundException;
import com.acelerati.gestionacademia.infraestructure.inputport.NivelEducativoPort;
import com.acelerati.gestionacademia.infraestructure.inputport.ProgramaAcademicoInputPort;
import com.acelerati.gestionacademia.infraestructure.outputport.ProgramaAcademicoRepositoryPort;
import org.springframework.stereotype.Service;

@Service

public class ProgramaAcademicoCasoUso implements ProgramaAcademicoInputPort {

    private static final String NO_EXISTE_NIVEL_EDUCATIVO = "no existe nivel educativo";
    private static final String NOMBRE_PROGRAMA_UNICO = "el nombre de cada programa es unico";

    private final NivelEducativoPort nivelEducativoPort;

    private final ProgramaAcademicoRepositoryPort programaAcademicoRepositoryPort;


    public ProgramaAcademicoCasoUso(NivelEducativoPort nivelEducativoPort, ProgramaAcademicoRepositoryPort programaAcademicoRepositoryPort) {
        this.nivelEducativoPort = nivelEducativoPort;
        this.programaAcademicoRepositoryPort = programaAcademicoRepositoryPort;
    }

    @Override
    public ProgramaAcademico crearProgramaAcademico(ProgramaAcademico programaAcademico) {
        programaAcademico.validarLongitudNombre();

        if (this.existeNombre(programaAcademico.getNombre())) {
            throw new BadRequestException(NOMBRE_PROGRAMA_UNICO);
        }

        programaAcademico.validarLetras();
        programaAcademico.validarLongitudDescripcion();

        if (!this.nivelEducativoPort.existeId(programaAcademico.getIdNivelEducativo())) {
            throw new NotFoundException(NO_EXISTE_NIVEL_EDUCATIVO);
        }
        return this.programaAcademicoRepositoryPort.crearProgramaAcademico(programaAcademico);
    }


    @Override
    public ProgramaAcademico buscarId(Long id) {
        return this.programaAcademicoRepositoryPort.buscarId(id);
    }

    @Override
    public Boolean existeNombre(String nombre) {
        return this.programaAcademicoRepositoryPort.existeNombre(nombre);
    }

    @Override
    public ProgramaAcademico eliminarId(Long id) {
        ProgramaAcademico programaAcademico = this.buscarId(id);
        programaAcademico.validarDirector();
        programaAcademico.validarConPensum();
        this.programaAcademicoRepositoryPort.eliminarId(id);
        return programaAcademico;
    }

    @Override
    public ProgramaAcademico asignarDirector(Long idPrograma, Long idDirector) {
        ProgramaAcademico programaAcademico = this.buscarId(idPrograma);
        programaAcademico.validarDirector();
        programaAcademico.setIdDirector(idDirector);
        this.programaAcademicoRepositoryPort.actualizarDirector(idPrograma,
                idDirector);
        return programaAcademico;
    }
}
