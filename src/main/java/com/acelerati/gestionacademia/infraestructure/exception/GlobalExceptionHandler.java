package com.acelerati.gestionacademia.infraestructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {SoloLetrasException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> letterNoValidException(
            HttpServletRequest request, SoloLetrasException apiRequestException) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiError apiError = new ApiError(apiRequestException.getMessage(),
                httpStatus,
                httpStatus.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, httpStatus);
    }


    @ExceptionHandler(value = {NoExisteNivelEducativoException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> noExisteNivelEducativoException(
            HttpServletRequest request, NoExisteNivelEducativoException apiRequestException) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiError apiError = new ApiError(apiRequestException.getMessage(),
                httpStatus,
                httpStatus.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, httpStatus);
    }
}

