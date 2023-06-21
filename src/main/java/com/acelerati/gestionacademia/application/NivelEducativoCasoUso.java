package com.acelerati.gestionacademia.application;

import com.acelerati.gestionacademia.infraestructure.inputport.NivelEducativoPort;
import com.acelerati.gestionacademia.infraestructure.outputport.NivelEducativoRepositoryPort;
import org.springframework.stereotype.Service;

@Service
public class NivelEducativoCasoUso implements NivelEducativoPort {

    private final NivelEducativoRepositoryPort nivelEducativoRepositoryPort;


    public NivelEducativoCasoUso(NivelEducativoRepositoryPort nivelEducativoRepositoryPort) {
        this.nivelEducativoRepositoryPort = nivelEducativoRepositoryPort;
    }

    @Override
    public Boolean existeId(Long id) {
        return this.nivelEducativoRepositoryPort.existeId(id);
    }
}
