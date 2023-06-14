package com.acelerati.gestionacademia.infraestructure.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "materia")
public class MateriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nombre;

    private String descripcion;

    @Column(name = "id_pensum")
    private Long idPensum;

    @Column(name = "id_materia_prerequisito")
    private Long idMateriaPrerequisito;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pensum", referencedColumnName = "id", updatable = false,
            insertable = false)
    private PensumEntity pensumEntity;

    @OneToOne()
    @JoinColumn(name = "id_materia_prerequisito", referencedColumnName = "id",
            insertable = false, updatable = false)
    private MateriaEntity materiaEntityPrerequisito;
}

