package com.acelerati.gestionacademia.domain;

import com.acelerati.gestionacademia.domain.util.ValidacionGeneral;
import com.acelerati.gestionacademia.infraestructure.entity.MateriaEntity;
import com.acelerati.gestionacademia.infraestructure.entity.PensumEntity;
import com.acelerati.gestionacademia.infraestructure.exception.BadRequestException;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
public class Materia {


    private Long id;


    private String nombre;

    private String descripcion;

    private Long idPensum;

    private Long idMateriaPrerequisito;

    //    @JsonIgnore
    private Pensum pensumEntity;

    //    @JsonIgnore
    private Materia materiaEntityPrerequisito;

    public Materia(Long id, String nombre, String descripcion, Long idPensum, Long idMateriaPrerequisito) {
        this.id = id;
        ValidacionGeneral.validarLongitud(nombre, "nombre", 2, 30);
        this.nombre = nombre;
        ValidacionGeneral.validarLongitud(descripcion, "descripcion", 6, 199);
        this.descripcion = descripcion;
        this.idPensum = idPensum;
        this.idMateriaPrerequisito = idMateriaPrerequisito;
    }


    public void validarPrerequisito() {
        if (!Objects.equals(this.materiaEntityPrerequisito.idPensum, this.idPensum)) {
            throw new BadRequestException("Materia prerequisito pensum diferente");
        }
    }
}
