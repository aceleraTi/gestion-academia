package com.acelerati.gestionacademia.infraestructure.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class NivelEducativoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "nivelEducativoEntity")
    List<ProgramaAcademicoEntity> programaAcademicoEntities;
}

