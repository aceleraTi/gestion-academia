package com.acelerati.gestionacademia.infraestructure.outputadapter;

import com.acelerati.gestionacademia.domain.Pensum;
import com.acelerati.gestionacademia.infraestructure.exception.BadRequestException;
import com.acelerati.gestionacademia.infraestructure.outputport.jparepository.PensumJPARepository;
import com.acelerati.gestionacademia.infraestructure.mapper.PensumMapper;
import com.acelerati.gestionacademia.infraestructure.outputport.PensumRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@RequiredArgsConstructor
public class PensumRepositoryMsql implements PensumRepositoryPort {

    private static final String NO_EXISTE_PENSUM = "el id del del pensum no existe";

    private final PensumJPARepository pensumJPARepository;

    private final PensumMapper pensumMapper;

    @Override
    public Pensum crearPensum(Pensum pensum) {
        return this.pensumMapper
                .toPensum(this.pensumJPARepository.save(this.pensumMapper.toPensumEntity(pensum)));
    }

    @Override
    public void eliminarId(Long id) {

    }

    @Override
    public Pensum obtenerPensum(Long id) {
        return this.pensumJPARepository.findById(id).map(
                this.pensumMapper::toPensum
        ).orElseThrow(() -> new BadRequestException(NO_EXISTE_PENSUM));
    }

    @Override
    public Boolean existeId(Long id) {
        return this.pensumJPARepository.existsById(id);
    }
}
