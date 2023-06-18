package com.acelerati.gestionacademia.application;

import com.acelerati.gestionacademia.domain.Materia;
import com.acelerati.gestionacademia.infraestructure.inputport.MateriaPort;
import com.acelerati.gestionacademia.infraestructure.outputport.MateriaRepositoryPort;
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
        materia.validarLongitudNombre();
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
    public List<Materia> materiasIdPensum(Long idPensum) {
        return this.materiaRepositoryPort.materiasIdPensum(idPensum);
    }
}
