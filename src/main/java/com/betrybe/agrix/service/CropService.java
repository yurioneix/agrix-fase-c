package com.betrybe.agrix.service;

import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.CropRepository;
import com.betrybe.agrix.model.repositories.FertilizerRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service de plantação.
 */
@Service
public class CropService {

  private CropRepository cropRepository;
  private FertilizerRepository fertilizerRepository;

  @Autowired
  public CropService(
      CropRepository cropRepository,
      FertilizerRepository fertilizerRepository
  ) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
  }

  public Optional<Crop> getCropById(Long cropId) {
    return cropRepository.findById(cropId);
  }

  public List<Crop> getAllCrops() {
    return cropRepository.findAll();
  }

  public Crop saveCrop(Crop crop) {
    return cropRepository.save(crop);
  }

  public Optional<List<Crop>> getCropByHarvestDate(LocalDate start, LocalDate end) {
    Optional<List<Crop>> crops = cropRepository.findAllCropByHarvestDateBetween(start, end);
    return crops;
  }

  /**
   * Método que associa uma plantação com um fertilizante.
   */
  public void associateCropWithFertilizer(Crop crop, Fertilizer fertilizer) {

    fertilizer.getCrops().add(crop);
    fertilizer.setCrops(crop);
    crop.getFertilizers().add(fertilizer);
    cropRepository.save(crop);
    fertilizerRepository.save(fertilizer);
  }
}
