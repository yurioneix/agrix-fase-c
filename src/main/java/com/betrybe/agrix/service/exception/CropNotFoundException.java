package com.betrybe.agrix.service.exception;

/**
 * Exceção que lida com Crop não encontrada.
 */
public class CropNotFoundException extends RuntimeException {
  public CropNotFoundException() {
    super("Plantação não encontrada!");
  }
}
