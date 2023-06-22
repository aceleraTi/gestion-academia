package com.acelerati.gestionacademia.infraestructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Schema(description = "Objecto para crear los pensums")
public class PensumPostDto {


    @JsonProperty(required = true)
    @NotNull(message = "el campo anio es requerido")
    @Schema(description = "anio del pensum",
            example = "2020")
    private Integer anio;


    @JsonProperty(required = true)
    @NotNull(message = "el campo idProgramaAcademico es requerido")
    @Schema(description = "id del programa academico",
            example = "1")
    private Long idProgramaAcademico;


}
