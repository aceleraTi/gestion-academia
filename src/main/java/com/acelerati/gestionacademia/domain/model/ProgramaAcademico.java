package com.acelerati.gestionacademia.domain.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Dominio del programa academico.")
public class ProgramaAcademico {

    private static final String LETTER_NOT_VALID = "El nombre debe contener solo letras.";


    private Long id;

    @Schema(description = "Nombre del programa academico",
            example = "Ingenieria de sistemas")
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

