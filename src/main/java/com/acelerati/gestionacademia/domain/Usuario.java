package com.acelerati.gestionacademia.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {

    private Long usuarioId;
    private String nombre;
    private String apellido;
    private String email;
    private String codigo;
    private Long tipoUsuario;
    private String mensaje;
}
