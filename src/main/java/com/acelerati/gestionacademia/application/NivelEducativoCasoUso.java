package com.acelerati.gestionacademia.application;

import com.acelerati.gestionacademia.infraestructure.inputport.NivelEducativoPort;
import com.acelerati.gestionacademia.infraestructure.outputport.NivelEducativoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NivelEducativoCasoUso implements NivelEducativoPort {

    private final NivelEducativoRepositoryPort nivelEducativoRepositoryPort;

    @Override
    public Boolean existeId(Long id) {
        return this.nivelEducativoRepositoryPort.existeId(id);
    }
}
