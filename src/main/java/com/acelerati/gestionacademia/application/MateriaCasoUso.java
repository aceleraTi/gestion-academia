package com.acelerati.gestionacademia.application;

import com.acelerati.gestionacademia.domain.Materia;
import com.acelerati.gestionacademia.infraestructure.inputport.MateriaPort;
import com.acelerati.gestionacademia.infraestructure.outputport.MateriaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MateriaCasoUso implements MateriaPort {

    private final MateriaRepositoryPort materiaRepositoryPort;

    @Override
    public Materia obtenerMateria(Long id) {
        return null;
    }

    @Override
    public Boolean existeMateria(Long id) {
        return null;
    }
}
