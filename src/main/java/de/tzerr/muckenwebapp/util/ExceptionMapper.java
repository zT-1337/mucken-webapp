package de.tzerr.muckenwebapp.util;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionMapper {

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<String> handleValidationError(ConstraintViolationException e) {
    return ResponseEntity.badRequest().body(e.getMessage());
  }
}
