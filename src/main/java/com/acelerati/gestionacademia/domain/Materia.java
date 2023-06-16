package com.acelerati.gestionacademia.domain;

import com.acelerati.gestionacademia.domain.util.ValidacionGeneral;
import com.acelerati.gestionacademia.infraestructure.exception.BadRequestException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class Materia {


    private Long id;

    private String nombre;

    private String descripcion;

    private Long idPensum;

    private Long idMateriaPrerequisito;

    private Materia materiaPrerequisito;


    public void validarNombre() {
        ValidacionGeneral.validarLongitud(this.nombre, "nombre", 2, 30);
    }

    public void validarDescripcion(){
        ValidacionGeneral.validarLongitud(descripcion, "descripcion", 6, 199);
    }


    public void validarPrerequisito() {
        if (!Objects.equals(this.materiaPrerequisito.idPensum, this.idPensum)) {
            throw new BadRequestException("Materia prerequisito pensum diferente");
        }
    }
}
