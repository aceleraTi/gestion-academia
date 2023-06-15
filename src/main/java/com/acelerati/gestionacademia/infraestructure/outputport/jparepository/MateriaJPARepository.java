package com.acelerati.gestionacademia.infraestructure.outputport.jparepository;

import com.acelerati.gestionacademia.infraestructure.entity.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaJPARepository extends JpaRepository<MateriaEntity, Long> {

}
