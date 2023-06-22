package com.acelerati.gestionacademia.infraestructure.outputport;

import com.acelerati.gestionacademia.domain.Pensum;

public interface PensumRepositoryPort {

    Pensum crearPensum(Pensum pensum);

    void eliminarId(Long id);


    Pensum obtenerPensum(Long id);

    Boolean existeId(Long id);

}
