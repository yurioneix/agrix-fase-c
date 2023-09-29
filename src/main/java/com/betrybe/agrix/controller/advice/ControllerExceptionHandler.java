package com.betrybe.agrix.controller.advice;

import com.betrybe.agrix.controller.exception.CropNotFoundException;
import com.betrybe.agrix.controller.exception.FarmNotFoundException;
import com.betrybe.agrix.controller.exception.FertilizerNotFoundException;
import com.betrybe.agrix.controller.exception.PersonNotFoundException;
import com.betrybe.agrix.controller.exception.UsernameNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe de controle de exceções.
 */
@ControllerAdvice
public class ControllerExceptionHandler {

  /**
   * Método que lida com a exceção de Farm não encontrado.
   */
  @ExceptionHandler({
      FarmNotFoundException.class,
      CropNotFoundException.class,
      FertilizerNotFoundException.class,
      PersonNotFoundException.class
  })
  public ResponseEntity<String> handleNotFoundException(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
  }

  @ExceptionHandler({
      UsernameNotFoundException.class
  })
  public ResponseEntity<String> handleForbiddenException(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(exception.getMessage());
  }
}
