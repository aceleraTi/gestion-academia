package com.acelerati.gestionacademia.application;

import com.acelerati.gestionacademia.domain.Pensum;
import com.acelerati.gestionacademia.domain.ProgramaAcademico;
import com.acelerati.gestionacademia.infraestructure.inputport.PensumInputPort;
import com.acelerati.gestionacademia.infraestructure.inputport.ProgramaAcademicoInputPort;
import com.acelerati.gestionacademia.infraestructure.outputport.PensumRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PensumCasoUso implements PensumInputPort {

    private final ProgramaAcademicoInputPort programaAcademicoInputPort;

    private final PensumRepositoryPort pensumRepositoryPort;

    @Override
    public Pensum crearPensum(Pensum pensum) {
        ProgramaAcademico programaAcademico = programaAcademicoInputPort.buscarId(pensum.getId());
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
}
