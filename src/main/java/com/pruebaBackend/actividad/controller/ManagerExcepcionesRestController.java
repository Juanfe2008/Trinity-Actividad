package com.pruebaBackend.actividad.controller;


import com.pruebaBackend.actividad.dto.RespuestaError;
import com.pruebaBackend.actividad.exceptions.BadRequestException;
import com.pruebaBackend.actividad.exceptions.ObjetNotExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ManagerExcepcionesRestController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ObjetNotExistException.class)
  public ResponseEntity<RespuestaError> objetoNoExistente(ObjetNotExistException ex) {
    var respuesta = new RespuestaError(ex.getMessage());
    return new ResponseEntity<>(respuesta, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<RespuestaError> BadRequestActivity(BadRequestException ex) {
    var respuesta = new RespuestaError(ex.getMessage());
    return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({IllegalArgumentException.class, RuntimeException.class})
  public ResponseEntity<RespuestaError> errorInternoDelServidor(Exception ex) {
    ex.printStackTrace();
    var respuesta = new RespuestaError(ex.getMessage());
    return new ResponseEntity<>(respuesta, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<RespuestaError> datosErroneos(Exception ex) {
    ex.printStackTrace();
    var respuesta = new RespuestaError(ex.getMessage());
    return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      @NonNull MethodArgumentNotValidException exception, @NonNull HttpHeaders headers,
      @NonNull HttpStatus status,
      WebRequest request) {
    List<String> validationErrors = exception.getBindingResult()
        .getFieldErrors()
        .stream()
        .map(error -> error.getField() + ": " + error.getDefaultMessage())
        .collect(Collectors.toList());
    return getExceptionResponseEntity(HttpStatus.BAD_REQUEST, validationErrors);
  }
  private ResponseEntity<Object> getExceptionResponseEntity(final HttpStatus status,
      List<String> errors) {
    List<RespuestaError> errores =
        !CollectionUtils.isEmpty(errors) ? errors.stream().map(RespuestaError::new)
            .collect(Collectors.toList())
            : Collections.singletonList(new RespuestaError(status.getReasonPhrase()));
    return new ResponseEntity<>(errores, status);
  }

}
