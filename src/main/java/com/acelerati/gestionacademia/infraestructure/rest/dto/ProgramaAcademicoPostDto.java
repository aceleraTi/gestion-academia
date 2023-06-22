package com.acelerati.gestionacademia.infraestructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@Schema(description = "Objecto para crear los programas academicos")
public class ProgramaAcademicoPostDto {

    @JsonProperty(required = true)
    @NotNull(message = "el campo nombre es requerido")
    @Schema(description = "Nombre del programa academico",
            example = "IngenieriaSistemas")
    private String nombre;


    @JsonProperty(required = true)
    @NotNull(message = "el campo descripcion es requerido")
    @Schema(description = "descripcion del programa academico", example = "Formar ingenieros líderes en investigación y aplicación de nuevas" +
            " tecnologías informáticas, para el apoyo a entidades estatales y privadas o para " +
            "la creación de nuevas empresas.")
    private String descripcion;

    @JsonProperty(required = true)
    @NotNull(message = "el campo idNivelEducativo es requerido")
    @Schema(description = "Id del nivel educativo", example = "1")
    private Long idNivelEducativo;


}
