package com.acelerati.gestionacademia.domain.enums;


import lombok.Getter;

@Getter
public enum TipoUsuarioEnum {
    DECANO(1L),
    DIRECTOR(2L),
    PROFESOR(3L),
    ALUMNO(4L);

    private Long id;

    TipoUsuarioEnum(Long id) {
        this.id = id;
    }

}
