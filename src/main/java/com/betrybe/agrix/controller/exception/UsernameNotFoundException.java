package com.betrybe.agrix.controller.exception;

public class UsernameNotFoundException extends RuntimeException {

  public UsernameNotFoundException() {
    super("Usuário não encontrado!");
  }
}
