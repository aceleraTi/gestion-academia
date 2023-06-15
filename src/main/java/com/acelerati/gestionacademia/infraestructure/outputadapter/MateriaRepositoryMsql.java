package com.acelerati.gestionacademia.infraestructure.outputadapter;

import com.acelerati.gestionacademia.domain.Materia;
import com.acelerati.gestionacademia.domain.ProgramaAcademico;
import com.acelerati.gestionacademia.infraestructure.exception.BadRequestException;
import com.acelerati.gestionacademia.infraestructure.exception.NoExisteProgramaAcademicoException;
import com.acelerati.gestionacademia.infraestructure.mapper.MateriaMapper;
import com.acelerati.gestionacademia.infraestructure.outputport.MateriaRepositoryPort;
import com.acelerati.gestionacademia.infraestructure.outputport.jparepository.MateriaJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MateriaRepositoryMsql implements MateriaRepositoryPort {
    private static final String NO_EXISTE_MATERIA = "no existe materia por el id";

    private final MateriaJPARepository materiaJPARepository;

    private final MateriaMapper materiaMapper;

    @Override
    public Materia buscarId(Long id) {
        return this.materiaJPARepository.findById(id)
                .map(this.materiaMapper::toMateria)
                .orElseThrow(() -> new BadRequestException(NO_EXISTE_MATERIA));
    }

    @Override
    public Boolean existeId(Long id) {
        return this.materiaJPARepository.existsById(id);
    }

}
