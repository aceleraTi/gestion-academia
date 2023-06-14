package com.acelerati.gestionacademia.infraestructure.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {

    private final String message;
    private final HttpStatus httpStatus;
    private final Integer httpStatusCode;
    //    private final String path;
    private final LocalDateTime timestamp;
}
