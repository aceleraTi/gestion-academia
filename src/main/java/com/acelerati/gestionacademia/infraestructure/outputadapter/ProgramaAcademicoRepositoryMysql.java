package com.acelerati.gestionacademia.infraestructure.outputadapter;

import com.acelerati.gestionacademia.domain.ProgramaAcademico;
import com.acelerati.gestionacademia.infraestructure.mapper.ProgramaAcademicoMapper;
import com.acelerati.gestionacademia.infraestructure.outputport.ProgramaAcademicoJPARepository;
import com.acelerati.gestionacademia.infraestructure.outputport.ProgramaAcademicoRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ProgramaAcademicoRepositoryMysql implements ProgramaAcademicoRepositoryPort {

    private final ProgramaAcademicoJPARepository programaAcademicoRepository;


    private final ProgramaAcademicoMapper programaAcademicoMapper;

    @Override
    public ProgramaAcademico save(ProgramaAcademico programaAcademico) {
        return programaAcademicoMapper.toProgramaAcademico(
                this.programaAcademicoRepository.save(
                        this.programaAcademicoMapper.toProgramaAcademicoEntity(programaAcademico)
                )
        );
    }

    @Override
    public void deleteById(Long idProgramaAcademico) {

    }
}