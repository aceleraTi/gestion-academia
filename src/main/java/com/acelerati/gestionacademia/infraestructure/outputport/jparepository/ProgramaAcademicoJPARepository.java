package com.acelerati.gestionacademia.infraestructure.outputport.jparepository;

import com.acelerati.gestionacademia.infraestructure.entity.ProgramaAcademicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramaAcademicoJPARepository extends JpaRepository<ProgramaAcademicoEntity, Long> {

    Boolean existsByNombreLikeIgnoreCase(String nombre);


}

