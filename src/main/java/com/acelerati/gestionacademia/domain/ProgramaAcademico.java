package com.acelerati.gestionacademia.domain;

import com.acelerati.gestionacademia.infraestructure.exception.LetterNotValidException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProgramaAcademico {

    private static final String LETTER_NOT_VALID = "el nombre debe contener solo letras.";


    private Long id;

    @Schema(example = "Ingenieria de sistemas")
    private String nombre;

    private String descripcion;

    private Long idNivelEducativo;

    private Long idDirector;


    ProgramaAcademico(String nombre, String descripcion,
                      Long idNivelEducativo, Long idDirector) {

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idNivelEducativo = idNivelEducativo;
        this.idDirector = idDirector;
    }

    private String validarLetras(String nombre) {
        for (int i = 0; i < nombre.length(); i++) {
            int codigoAscci = nombre.charAt(i);
            if (codigoAscci < 65 || codigoAscci > 122) {
                throw new LetterNotValidException(LETTER_NOT_VALID);
            }
            if (codigoAscci > 90 && codigoAscci < 97) {
                throw new LetterNotValidException(LETTER_NOT_VALID);
            }
        }
        return nombre;
    }

}

