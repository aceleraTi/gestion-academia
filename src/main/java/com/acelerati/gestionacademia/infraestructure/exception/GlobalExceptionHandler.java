package com.acelerati.gestionacademia.infraestructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {LetterNotValidException.class})
    public ResponseEntity<Object> letterNoValidException(
            HttpServletRequest request, LetterNotValidException apiRequestException) {
        HttpStatus unauthorized = HttpStatus.UNAUTHORIZED;
        ApiError apiError = new ApiError(apiRequestException.getMessage(),
                unauthorized,
                unauthorized.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, unauthorized);
    }
}

