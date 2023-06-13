package com.acelerati.gestionacademia.infraestructure.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class PensumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "anno")
    private LocalDate anio;

    @Column(name = "id_programa_academico")
    private Long idProgramaAcademico;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programa_academico", referencedColumnName = "id",
            insertable = false, updatable = false)
    private ProgramaAcademicoEntity programaAcademicoEntity;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pensumEntity")
    private List<MateriaEntity> materiaEntities;
}