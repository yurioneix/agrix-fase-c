package com.betrybe.agrix.controller.dto;

import com.betrybe.agrix.security.Role;

/**
 * Dto de resposta da classe Person.
 */
public record PersonResponseDto(Long id, String username, Role role) {

}
