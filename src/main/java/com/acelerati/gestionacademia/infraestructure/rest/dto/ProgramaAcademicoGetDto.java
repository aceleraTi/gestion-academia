package com.acelerati.gestionacademia.infraestructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProgramaAcademicoGetDto {
    private Long id;

    private String nombre;

    private String descripcion;

    private Long idNivelEducativo;

    private Long idDirector;
}