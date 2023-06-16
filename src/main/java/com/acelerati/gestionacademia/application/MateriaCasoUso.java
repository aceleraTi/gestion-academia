package com.acelerati.gestionacademia.application;

import com.acelerati.gestionacademia.domain.Materia;
import com.acelerati.gestionacademia.infraestructure.inputport.MateriaPort;
import com.acelerati.gestionacademia.infraestructure.outputport.MateriaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaCasoUso implements MateriaPort {

    private final MateriaRepositoryPort materiaRepositoryPort;

    public MateriaCasoUso(MateriaRepositoryPort materiaRepositoryPort) {
        this.materiaRepositoryPort = materiaRepositoryPort;
    }

    @Override
    public Materia crearMateria(Materia materia) {
        materia.validarNombre();
        materia.validarDescripcion();
        if (materia.getIdMateriaPrerequisito() != null) {
            materia.setMateriaPrerequisito(this.obtenerMateria(materia.getIdMateriaPrerequisito()));
            materia.validarPrerequisito();
        }
        return this.materiaRepositoryPort.crearMateria(materia);
    }

    @Override
    public Materia obtenerMateria(Long id) {
        return this.materiaRepositoryPort.buscarId(id);
    }

    @Override
    public Boolean existeMateria(Long id) {
        return null;
    }

    @Override
    public List<Materia> materiasIdPensum(Long idPensum) {
        return this.materiaRepositoryPort.materiasIdPensum(idPensum);
    }
}
