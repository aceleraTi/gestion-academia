package com.acelerati.gestionacademia.infraestructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Objecto para obtener la informacion de los programas academicos")

public class ProgramaAcademicoGetDto {

    @Schema(description = "Id del programa academico",
            example = "IngenieriaSistemas")
    private Long id;

    @Schema(description = "Nombre del programa academico",
            example = "IngenieriaSistemas")
    private String nombre;

    @Schema(description = "Descripcion del programa academico",
            example = "IngenieriaSistemas")
    private String descripcion;

    @Schema(description = "Id del nivel educativo del programa academico",
            example = "IngenieriaSistemas")
    private Long idNivelEducativo;

    @Schema(description = "Id del director del programa academico",
            example = "IngenieriaSistemas")
    private Long idDirector;
}
