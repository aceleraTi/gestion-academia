package com.acelerati.gestionacademia.infraestructure.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
