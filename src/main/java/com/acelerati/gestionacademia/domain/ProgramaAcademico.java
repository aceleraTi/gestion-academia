package com.acelerati.gestionacademia.domain;

import com.acelerati.gestionacademia.domain.util.ValidacionGeneral;
import com.acelerati.gestionacademia.infraestructure.exception.ExistePensumException;
import com.acelerati.gestionacademia.infraestructure.exception.LongitudException;
import com.acelerati.gestionacademia.infraestructure.exception.SoloLetrasException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.Year;
import java.util.List;

@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
@Schema(description = "Dominio del programa academico.")
public class ProgramaAcademico {

    private static final String ERROR_LETRAS = "el nombre debe contener solo letras";
    private static final String ERROR_EXISTE_PENSUM = "no se puede crear mas de 1 pensum para el mismo " +
            "anio";


    private Long id;

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

    //    @JsonIgnore
    private List<Pensum> pensumEntities;


    public ProgramaAcademico(Long id, String nombre, String descripcion,
                             Long idNivelEducativo, Long idDirector,
                             List<Pensum> pensumEntities) {
        this.id = id;
        ValidacionGeneral.validarLongitud(nombre, "nombre", 8, 40);
        this.validarLetras(nombre);
        this.nombre = nombre;
        ValidacionGeneral.validarLongitud(descripcion, "descripcion", 20, 200);
        this.descripcion = descripcion;
        this.idNivelEducativo = idNivelEducativo;
        this.idDirector = idDirector;
        this.pensumEntities = pensumEntities;
    }


    public void validarLetras(String nombre) {
        int tamanio = nombre.length();
        for (int i = 0; i < tamanio; i++) {
            if (nombre.charAt(i) < 65 || nombre.charAt(i) > 122) {
                throw new SoloLetrasException(ERROR_LETRAS);
            }
            if (nombre.charAt(i) > 90 && nombre.charAt(i) < 97) {
                throw new SoloLetrasException(ERROR_LETRAS);
            }
        }
    }

    public void existePensumAnio(Integer anio) {
        if (pensumEntities.stream().anyMatch(pensum -> pensum.getAnio().equals(anio))) {
            throw new ExistePensumException(ERROR_EXISTE_PENSUM);
        }
    }

}

