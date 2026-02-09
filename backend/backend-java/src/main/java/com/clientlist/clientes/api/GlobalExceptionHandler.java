package com.clientlist.clientes.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.clientlist.clientes.exception.ClienteNaoEncontradoException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ClienteNaoEncontradoException.class)
  public ResponseEntity<ApiError> handleClienteNaoEncontrado( ClienteNaoEncontradoException ex, HttpServletRequest request) {

    ApiError error = new ApiError(
      HttpStatus.NOT_FOUND.value(),
      "CLIENTE_NAO_ENCONTRADO",
      ex.getMessage(),
      request.getRequestURI()
    );

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> handleValidation( MethodArgumentNotValidException ex, HttpServletRequest request) {

    List<String> errors = ex.getBindingResult()
      .getFieldErrors()
      .stream()
      .map(FieldError::getDefaultMessage)
      .toList();

      ApiError error = new ApiError(
        HttpStatus.BAD_REQUEST.value(),
        "VALIDATION_ERROR",
        errors,
        request.getRequestURI()
    );

      return ResponseEntity.badRequest().body(error);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ApiError> handleGeneric( Exception ex, HttpServletRequest request) {
    ApiError error = new ApiError(
      HttpStatus.INTERNAL_SERVER_ERROR.value(),
      "ERRO_INTERNO",
      "Erro interno no servidor",
      request.getRequestURI()
    );

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
  }
}

