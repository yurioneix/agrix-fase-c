package com.betrybe.agrix.controller.dto;

import java.time.LocalDate;

/**
 * Dto de resposta de Crop.
 */
public record CropResponseDto(
    Long id, String name,
    Double plantedArea,
    LocalDate plantedDate,
    LocalDate harvestDate,
    Long farmId
) {}
