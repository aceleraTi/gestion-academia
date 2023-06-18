package com.acelerati.gestionacademia.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
public enum TipoUsuario {
    DECANO(1L),
    DIRECTOR(2L),
    PROFESOR(3L),
    ALUMNO(4L);

    private Long id;

    TipoUsuario(Long id) {
        this.id = id;
    }

}
