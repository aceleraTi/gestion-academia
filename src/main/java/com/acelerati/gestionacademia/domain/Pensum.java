package com.acelerati.gestionacademia.domain;

import com.acelerati.gestionacademia.infraestructure.exception.ConflictException;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class Pensum {

    private static final String MATERIA_ASIGNADA = "el pensum cuenta con materias asignadas";

    private Long id;

    private Integer anio;

    private Long idProgramaAcademico;

    private List<Materia> materias;


    public void validarMateriaAsignadas() {
        if (materias.size() > 0) {
            throw new ConflictException(MATERIA_ASIGNADA);
        }
    }

}
