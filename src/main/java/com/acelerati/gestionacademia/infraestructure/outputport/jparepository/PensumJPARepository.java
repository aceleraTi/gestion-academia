package com.acelerati.gestionacademia.infraestructure.outputport.jparepository;

import com.acelerati.gestionacademia.infraestructure.entity.PensumEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PensumJPARepository extends JpaRepository<PensumEntity, Long> {
}
