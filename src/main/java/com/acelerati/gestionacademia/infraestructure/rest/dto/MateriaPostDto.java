package com.acelerati.gestionacademia.infraestructure.rest.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriaPostDto {
    private String nombre;

    private String descripcion;

    private Long idPensum;

    private Long idMateriaPrerequisito;

}
