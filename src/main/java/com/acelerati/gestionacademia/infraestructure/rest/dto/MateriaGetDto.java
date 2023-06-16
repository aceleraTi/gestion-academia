package com.acelerati.gestionacademia.infraestructure.rest.dto;

import com.acelerati.gestionacademia.domain.Materia;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriaGetDto {

    private Long id;

    private String nombre;

    private String descripcion;

    private Materia materiaPrerequisito;
}
