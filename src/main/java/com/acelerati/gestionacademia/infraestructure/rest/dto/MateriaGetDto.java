package com.acelerati.gestionacademia.infraestructure.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Objecto para obtener la informacion de las materias")
public class MateriaGetDto {

    @Schema(description = "id de la materia",
            example = "1")
    private Long id;


    @Schema(description = "nombre de la materia",
            example = "calculo I")
    private String nombre;

    @Schema(description = "descripcion de la materia",
            example = "En este curso se desarrolla la teoría básica del cálculo diferencial de " +
                    "funciones reales de variable real (límites, continuidad y diferenciabilidad) y " +
                    "sus principales aplicaciones.")
    private String descripcion;

    @Schema(description = "pensum de la materia")
    private PensumGetDto pensum;


    @Schema(description = "materia prerequisito de la materia")
    private MateriaGetDto materiaPrerequisito;
}
