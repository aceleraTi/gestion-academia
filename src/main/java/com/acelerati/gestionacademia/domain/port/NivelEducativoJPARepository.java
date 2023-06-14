package com.acelerati.gestionacademia.domain.port;

import com.acelerati.gestionacademia.infraestructure.entity.NivelEducativoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NivelEducativoJPARepository extends JpaRepository<NivelEducativoEntity, Long> {


}
