package com.acelerati.gestionacademia.domain;

import com.acelerati.gestionacademia.domain.util.ValidacionGeneral;
import com.acelerati.gestionacademia.infraestructure.exception.BadRequestException;
import com.acelerati.gestionacademia.infraestructure.exception.ConflictException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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
    private static final String ERROR_DIRECTOR = "tiene un director asignado";

    private static final String ERROR_PENSUM = "no se puede eliminar, tiene pensums asociados";


    private Long id;

    private String nombre;

    private String descripcion;

    private Long idNivelEducativo;

    private Long idDirector;

    private List<Pensum> pensums;


    public void validarLongitudNombre() {
        ValidacionGeneral.validarLongitud(nombre, "nombre", 8, 40);
    }

    public void validarLongitudDescripcion() {
        ValidacionGeneral.validarLongitud(descripcion, "descripcion", 20, 200);
    }

    public void validarLetras() {
        int tamanio = this.nombre.length();
        for (int i = 0; i < tamanio; i++) {
            if (this.nombre.charAt(i) < 65 || this.nombre.charAt(i) > 122) {
                throw new BadRequestException(ERROR_LETRAS);
            }
            if (this.nombre.charAt(i) > 90 && this.nombre.charAt(i) < 97) {
                throw new BadRequestException(ERROR_LETRAS);
            }
        }
    }

    public void existePensumAnio(Integer anio) {
        if (pensums.stream().anyMatch(pensum -> pensum.getAnio().equals(anio))) {
            throw new BadRequestException(ERROR_EXISTE_PENSUM);
        }
    }

    public void validarDirector() {
        if (this.idDirector != null) {
            throw new ConflictException(ERROR_DIRECTOR);
        }
    }

    public void validarConPensum() {
        if (this.pensums.size() > 0) {
            throw new ConflictException(ERROR_PENSUM);
        }
    }
}

