package com.acelerati.gestionacademia.infraestructure.rest.dto;

import com.acelerati.gestionacademia.domain.Materia;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PensumGetDto {

    private Long id;

    private Integer anio;

    private List<MateriaGetDto> materias;

}
