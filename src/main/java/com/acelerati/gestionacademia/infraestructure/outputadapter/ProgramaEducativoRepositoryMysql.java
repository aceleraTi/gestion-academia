package com.acelerati.gestionacademia.infraestructure.outputadapter;

import com.acelerati.gestionacademia.domain.ProgramaAcademico;
import com.acelerati.gestionacademia.infraestructure.exception.BadRequestException;
import com.acelerati.gestionacademia.infraestructure.exception.NotFoundException;
import com.acelerati.gestionacademia.infraestructure.mapper.ProgramaAcademicoMapper;
import com.acelerati.gestionacademia.infraestructure.outputport.jparepository.ProgramaAcademicoJPARepository;
import com.acelerati.gestionacademia.infraestructure.outputport.ProgramaAcademicoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component()
@RequiredArgsConstructor
public class ProgramaEducativoRepositoryMysql implements
        ProgramaAcademicoRepositoryPort {

    private static final String NO_EXISTE_PROGRAMA_ACADEMICO = "no existe un programa academico con ese Id";
    private final ProgramaAcademicoJPARepository programaAcademicoRepository;


    private final ProgramaAcademicoMapper programaAcademicoMapper;

    @Override
    public ProgramaAcademico crearProgramaAcademico(ProgramaAcademico programaAcademico) {
        return programaAcademicoMapper.toProgramaAcademico(
                this.programaAcademicoRepository.save(
                        this.programaAcademicoMapper.toProgramaAcademicoEntity(programaAcademico)
                )
        );
    }

    @Override
    public void eliminarId(Long idProgramaAcademico) {
        this.programaAcademicoRepository.deleteById(idProgramaAcademico);
    }

    @Override
    public ProgramaAcademico buscarId(Long id) {
        return this.programaAcademicoRepository.findById(id)
                .map(this.programaAcademicoMapper::toProgramaAcademico)
                .orElseThrow(() -> new BadRequestException(NO_EXISTE_PROGRAMA_ACADEMICO));
    }

    @Override
    public Boolean existeNombre(String nombre) {
        return this.programaAcademicoRepository.existsByNombreLikeIgnoreCase(nombre);
    }

    @Override
    public int actualizarDirector(Long idPrograma, Long idDirector) {
        return this.programaAcademicoRepository.actualizarDirector(idPrograma, idDirector);
    }

    @Override
    public Boolean existeId(Long id) {

        return this.programaAcademicoRepository.existsById(id);
    }
}