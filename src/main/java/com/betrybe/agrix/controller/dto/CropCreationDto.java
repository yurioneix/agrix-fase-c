package com.betrybe.agrix.controller.dto;

import java.time.LocalDate;

/**
 * Dto de criação de uma plantação.
 */
public record CropCreationDto(
    String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate
) {

}
