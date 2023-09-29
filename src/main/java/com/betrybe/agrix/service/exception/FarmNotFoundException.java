package com.betrybe.agrix.service.exception;

/**
 * Exceção que lida com farm não encontrado.
 */
public class FarmNotFoundException extends RuntimeException {
  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
