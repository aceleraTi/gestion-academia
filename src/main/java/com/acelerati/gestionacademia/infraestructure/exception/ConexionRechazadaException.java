package com.acelerati.gestionacademia.infraestructure.exception;

import java.net.ConnectException;

public class ConexionRechazadaException extends RuntimeException {

    public ConexionRechazadaException(String message) {
        super(message);
    }
}
