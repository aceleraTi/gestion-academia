package com.acelerati.gestionacademia.infraestructure.outputadapter;

import com.acelerati.gestionacademia.domain.port.NivelEducativoJPARepository;
import com.acelerati.gestionacademia.infraestructure.outputport.NivelEducativoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@RequiredArgsConstructor
public class NivelEducativoRepositoryMysql implements NivelEducativoRepositoryPort {

    private final NivelEducativoJPARepository nivelEducativoJPARepository;


    @Override
    public Boolean existsById(Long idNivelEducativo) {
        return this.nivelEducativoJPARepository.existsById(idNivelEducativo);
    }
}
