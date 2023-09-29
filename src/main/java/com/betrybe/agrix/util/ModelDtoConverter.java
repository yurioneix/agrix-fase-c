package com.betrybe.agrix.util;

import com.betrybe.agrix.controller.dto.FarmCreationDto;
import com.betrybe.agrix.controller.dto.FarmDto;
import com.betrybe.agrix.model.entities.Farm;

/**
 * Classe auxiliar que converte de DTO para model e vice versa.
 */
public class ModelDtoConverter {

  /**
   * Converte de model para DTO.
   */
  public static FarmDto modelToDto(Farm farm) {
    return new FarmDto(
        farm.getId(),
        farm.getName(),
        farm.getSize()
    );
  }

  /**
   * Converte de DTO para model.
   */
  public static Farm dtoToModel(FarmCreationDto dto) {
    Farm farm = new Farm();

    farm.setName(dto.name());
    farm.setSize(dto.size());

    return farm;
  }
}
