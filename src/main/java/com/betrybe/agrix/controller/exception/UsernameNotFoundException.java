package com.betrybe.agrix.controller.exception;

/**
 * Exceção de usuário não encontrado.
 */
public class UsernameNotFoundException extends RuntimeException {

  public UsernameNotFoundException() {
    super("Usuário não encontrado!");
  }
}
