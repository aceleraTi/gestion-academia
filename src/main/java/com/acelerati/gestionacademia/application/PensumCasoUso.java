package com.acelerati.gestionacademia.application;

import com.acelerati.gestionacademia.domain.Materia;
import com.acelerati.gestionacademia.domain.Pensum;
import com.acelerati.gestionacademia.domain.ProgramaAcademico;
import com.acelerati.gestionacademia.infraestructure.inputport.MateriaPort;
import com.acelerati.gestionacademia.infraestructure.inputport.PensumInputPort;
import com.acelerati.gestionacademia.infraestructure.inputport.ProgramaAcademicoInputPort;
import com.acelerati.gestionacademia.infraestructure.outputport.PensumRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PensumCasoUso implements PensumInputPort {

    private final ProgramaAcademicoInputPort programaAcademicoInputPort;

    private final PensumRepositoryPort pensumRepositoryPort;

    private final MateriaPort materiaPort;

    @Override
    public Pensum crearPensum(Pensum pensum) {
        ProgramaAcademico programaAcademico = programaAcademicoInputPort
                .buscarId(pensum.getIdProgramaAcademico());
        programaAcademico.existePensumAnio(pensum.getAnio());
        return this.pensumRepositoryPort.crearPensum(pensum);
    }

    @Override
    public Pensum obtenerPensum(Long id) {
        return this.pensumRepositoryPort.obtenerPensum(id);
    }

    @Override
    public Boolean existePensum(Long id) {
        return this.pensumRepositoryPort.existeId(id);
    }

    @Override
    public void eliminarId(Long id) {
        Pensum pensum = this.obtenerPensum(id);
        pensum.validarMateriaAsignadas();
        this.pensumRepositoryPort.eliminarId(id);
    }
}
