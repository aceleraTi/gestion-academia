package com.acelerati.gestionacademia.infraestructure.inputport;

import com.acelerati.gestionacademia.domain.Pensum;

public interface PensumInputPort {
    Pensum crearPensum(Pensum pensum);


    Pensum obtenerPensum(Long id);

    Boolean existePensum(Long id);

    Pensum eliminarId(Long id);
}
