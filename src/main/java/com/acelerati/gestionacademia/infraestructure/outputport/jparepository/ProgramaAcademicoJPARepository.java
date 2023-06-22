package com.acelerati.gestionacademia.infraestructure.outputport.jparepository;

import com.acelerati.gestionacademia.infraestructure.entity.ProgramaAcademicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface ProgramaAcademicoJPARepository extends JpaRepository<ProgramaAcademicoEntity, Long> {

    Boolean existsByNombreLikeIgnoreCase(String nombre);

    @Query("UPDATE ProgramaAcademicoEntity c SET c.idDirector = :idDirector WHERE c.id = " +
            ":idPrograma")
    @Modifying
    @Transactional
    int actualizarDirector(Long idPrograma,
                           Long idDirector);


    Optional<ProgramaAcademicoEntity> findByIdDirector(Long idDirector);


}

