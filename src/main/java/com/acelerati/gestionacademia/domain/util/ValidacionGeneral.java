package com.acelerati.gestionacademia.domain.util;

import com.acelerati.gestionacademia.infraestructure.exception.BadRequestException;

public class ValidacionGeneral {
    private static final String ERROR_LONGITUD = "la longitud del campo %s es de %s a %s " +
            "caracteres";

    public static void validarLongitud(String valor, String campo, int min, int max) {
        int tamanio = valor.length();
        if (tamanio < min || tamanio > max) {
            throw new BadRequestException(String.format(ERROR_LONGITUD, campo, min, max));
        }
    }
}
