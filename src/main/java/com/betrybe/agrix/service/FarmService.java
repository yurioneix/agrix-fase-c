package com.betrybe.agrix.service;

import com.betrybe.agrix.controller.dto.FarmCreationDto;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Farm;
import com.betrybe.agrix.model.repositories.CropRepository;
import com.betrybe.agrix.model.repositories.FarmRepository;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service da classe Farm.
 */
@Service
public class FarmService {
  private FarmRepository farmRepository;
  private CropRepository cropRepository;

  @Autowired
  public FarmService(
      FarmRepository farmRepository,
      CropRepository cropRepository
  ) {
    this.farmRepository = farmRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * Método que retorna todas as farms.
   */
  public List<Farm> getAllFarm() {
    return farmRepository.findAll();
  }

  /**
   * Método que retorna um Farm pelo id ou lança exceção caso não encontre.
   */
  public Optional<Farm> getFarmById(Long id) {

    return farmRepository.findById(id);
  }

  /**
   * Método de criação de uma fazenda.
   */
  public Farm createFarm(FarmCreationDto farmCreationDto) {
    Farm farm = new Farm();

    farm.setName(farmCreationDto.name());
    farm.setSize(farmCreationDto.size());

    return farmRepository.save(farm);
  }

  /**
   * Método que retorna todas as plantações através do farmId.
   */
  public List<Crop> getCropsByFarmId(Long farmId) {
    Optional<Farm> optionalFarm = farmRepository.findById(farmId);

    if (optionalFarm.isEmpty()) {
      throw new FarmNotFoundException();
    }

    Farm farm = optionalFarm.get();

    return farm.getCrops();
  }

  /**
   * Método que cria um crop a partir do id de um farm.
   */
  public Optional<Crop> createCropByFarmId(Long id, Crop crop) {
    Optional<Farm> optionalFarm = farmRepository.findById(id);

    Farm farm = optionalFarm.get();

    cropRepository.save(crop);
    farm.getCrops().add(crop);
    farmRepository.save(farm);

    crop.setFarm(farm);

    return Optional.of(crop);
  }
}
