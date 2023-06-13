package com.acelerati.gestionacademia.infraestructure.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "programa_academico")
public class ProgramaAcademicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 40, unique = true)
    @Size(min = 8)
    private String nombre;

    @Column(length = 200, unique = true)
    private String descripcion;

    @Column(name = "id_nivel_educativo")
    private Long idNivelEducativo;

    @Column(name = "id_director")
    private Long idDirector;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nivel_educativo", referencedColumnName = "id", updatable = false,
            insertable = false)
    private NivelEducativoEntity nivelEducativoEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "programaAcademicoEntity")
    private List<PensumEntity> pensumEntities;

}

