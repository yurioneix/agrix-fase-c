package com.betrybe.agrix.controller.dto;

/**
 * Dto de resposta da classe Person.
 */
public record PersonResponseDto(Long id, String username, com.betrybe.agrix.security.Role role) {

}
