package com.acelerati.gestionacademia.application;

import com.acelerati.gestionacademia.infraestructure.inputport.NivelEducativoInputPort;
import com.acelerati.gestionacademia.infraestructure.outputport.NivelEducativoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NivelEducativoService implements NivelEducativoInputPort {

    private final NivelEducativoRepositoryPort nivelEducativoRepositoryPort;

    @Override
    public Boolean existsById(Long idNivelAcademico) {
        return this.nivelEducativoRepositoryPort.existsById(idNivelAcademico);
    }
}
