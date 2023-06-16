package com.acelerati.gestionacademia.infraestructure.outputport.jparepository;

import com.acelerati.gestionacademia.infraestructure.entity.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MateriaJPARepository extends JpaRepository<MateriaEntity, Long> {

    List<MateriaEntity> findAllByIdPensum(Long idPensum);
}
