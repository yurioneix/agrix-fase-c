package com.betrybe.agrix.service.exception;

/**
 * Exceção que lida com Fertilizante não encontrado.
 */
public class FertilizerNotFoundException extends RuntimeException {
  public FertilizerNotFoundException() {
    super("Fertilizante não encontrado!");
  }

}
