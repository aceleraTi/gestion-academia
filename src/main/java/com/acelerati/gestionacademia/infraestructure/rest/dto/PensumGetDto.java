package com.acelerati.gestionacademia.infraestructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(description = "Objecto para obtener la informacion de los pensums")
public class PensumGetDto {
    @Schema(description = "id del pensum",
            example = "1")
    private Long id;
    @Schema(description = "anio del pensum",
            example = "2020")
    private Integer anio;

}
