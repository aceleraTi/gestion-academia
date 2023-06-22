package com.acelerati.gestionacademia.infraestructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.net.ConnectException;
import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> notFoundExceptionException(
            HttpServletRequest request, NotFoundException apiRequestException) {
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        ApiError apiError = new ApiError(apiRequestException.getMessage(),
                httpStatus,
                httpStatus.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, httpStatus);
    }


    @ExceptionHandler(value = {BadRequestException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> badRequestException(
            HttpServletRequest request,
            BadRequestException apiRequestException) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiError apiError = new ApiError(apiRequestException.getMessage(),
                httpStatus,
                httpStatus.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, httpStatus);
    }

    @ExceptionHandler(value = {ConflictException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ApiError> conflictException(
            HttpServletRequest request,
            ConflictException apiRequestException) {
        HttpStatus httpStatus = HttpStatus.CONFLICT;
        ApiError apiError = new ApiError(apiRequestException.getMessage(),
                httpStatus,
                httpStatus.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, httpStatus);
    }

    @ExceptionHandler(value = {ForbiddenException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<ApiError> conflictException(
            HttpServletRequest request,
            ForbiddenException apiRequestException) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        ApiError apiError = new ApiError(apiRequestException.getMessage(),
                httpStatus,
                httpStatus.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, httpStatus);
    }


    @ExceptionHandler(value = {ConexionRechazadaException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> conexionRechazadaException(
            HttpServletRequest request,
            ConexionRechazadaException apiRequestException) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiError apiError = new ApiError(apiRequestException.getMessage(),
                httpStatus,
                httpStatus.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, httpStatus);
    }


    @ExceptionHandler(value = {ApiException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiError> apiException(
            HttpServletRequest request,
            ApiException apiRequestException) {
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiError apiError = new ApiError(apiRequestException.getMessage(),
                httpStatus,
                httpStatus.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, httpStatus);
    }

    private static final String ENCABEZADO_REQUERIDO = "encabezado de solicitud requerido";

    @ExceptionHandler(value = {MissingRequestHeaderException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> missingRequestHeaderException(
            HttpServletRequest request,
            MissingRequestHeaderException apiRequestException) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiError apiError = new ApiError(ENCABEZADO_REQUERIDO,
                httpStatus,
                httpStatus.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, httpStatus);
    }

    private static final String CUERPO_REQUERIDO = "falta el cuerpo de solicitud requerido";

    @ExceptionHandler(value = {HttpMessageNotReadableException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> httpMessageNotReadableException(
            HttpServletRequest request,
            HttpMessageNotReadableException apiRequestException) {
        System.out.println(apiRequestException.getMessage());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiError apiError = new ApiError(CUERPO_REQUERIDO,
                httpStatus,
                httpStatus.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, httpStatus);
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ApiError> methodArgumentNotValidException(
            HttpServletRequest request,
            MethodArgumentNotValidException apiRequestException) {
        System.out.println(apiRequestException.getMessage());
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ApiError apiError = new ApiError(Objects.requireNonNull(apiRequestException.getFieldError()).getDefaultMessage(),
                httpStatus,
                httpStatus.value(),
                LocalDateTime.now());
        return new ResponseEntity<>(apiError, httpStatus);
    }

}

