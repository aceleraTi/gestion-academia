package com.acelerati.gestionacademia.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pensum")
public class PensumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "annio")
    private Integer anio;

    @Column(name = "id_programa_academico")
    private Long idProgramaAcademico;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programa_academico", referencedColumnName = "id",
            insertable = false, updatable = false)
    private ProgramaAcademicoEntity programaAcademicoEntity;


    @JsonBackReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pensumEntity")
    private List<MateriaEntity> materiaEntities;
}