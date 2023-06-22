package com.acelerati.gestionacademia.infraestructure.rest.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Schema(description = "Objecto para crear las materias")
public class MateriaPostDto {


    @JsonProperty(required = true)
    @NotNull(message = "el campo nombre es requerido")
    @Schema(description = "nombre de la materia",
            example = "calculo I")
    private String nombre;


    @JsonProperty(required = true)
    @NotNull(message = "el campo descripcion es requerido")
    @Schema(description = "descripcion de la materia",
            example = "En este curso se desarrolla la teoría básica del cálculo diferencial de " +
                    "funciones reales de variable real (límites, continuidad y diferenciabilidad) y " +
                    "sus principales aplicaciones.")
    private String descripcion;

    @JsonProperty(required = true)
    @NotNull(message = "el campo idPensum es requerido")
    @Schema(description = "el id del pensum",
            example = "calculo I")
    private Long idPensum;

    @Schema(description = "id de la materia prerequisito",
            example = "1")
    private Long idMateriaPrerequisito;

}
