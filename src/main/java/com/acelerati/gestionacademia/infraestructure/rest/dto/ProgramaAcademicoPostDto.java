package com.acelerati.gestionacademia.infraestructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProgramaAcademicoPostDto {

    @Schema(description = "Nombre del programa academico",
            example = "IngenieriaSistemas")
    private String nombre;

    @Schema(example = "Formar ingenieros líderes en investigación y aplicación de nuevas" +
            " tecnologías informáticas, para el apoyo a entidades estatales y privadas o para " +
            "la creación de nuevas empresas.")
    private String descripcion;


    @Schema(description = "Id del nivel educativo", example = "1")
    private Long idNivelEducativo;

    @Schema(description = "Id del director", example = "1")
    private Long idDirector;


}
