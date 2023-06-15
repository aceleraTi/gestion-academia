package com.acelerati.gestionacademia.infraestructure.outputadapter;

import com.acelerati.gestionacademia.infraestructure.outputport.jparepository.NivelEducativoJPARepository;
import com.acelerati.gestionacademia.infraestructure.outputport.NivelEducativoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NivelEducativoRepositoryMysql implements NivelEducativoRepositoryPort {

    private final NivelEducativoJPARepository nivelEducativoJPARepository;


    @Override
    public Boolean existeId(Long id) {
        return this.nivelEducativoJPARepository.existsById(id);
    }
}
