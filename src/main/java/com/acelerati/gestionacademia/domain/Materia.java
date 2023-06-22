package com.acelerati.gestionacademia.domain;

import com.acelerati.gestionacademia.domain.util.ValidacionGeneral;
import com.acelerati.gestionacademia.infraestructure.exception.BadRequestException;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Materia {

    private static final String ERROR_PREREQUISITO = "materia prerequisito es de un pensum " +
            "diferente";


    private Long id;

    private String nombre;

    private String descripcion;

    private Long idPensum;

    private Pensum pensum;

    private Long idMateriaPrerequisito;

    private Materia materiaPrerequisito;


    public void validarLongitudNombre() {
        ValidacionGeneral.validarLongitud(this.nombre, "nombre", 2, 30);
    }

    public void validarDescripcion() {
        ValidacionGeneral.validarLongitud(descripcion, "descripcion", 6, 199);
    }


    public void validarPrerequisito() {
        if (!Objects.equals(this.materiaPrerequisito.idPensum, this.idPensum)) {
            throw new BadRequestException(ERROR_PREREQUISITO);
        }
    }
}
