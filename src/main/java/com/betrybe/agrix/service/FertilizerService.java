package com.betrybe.agrix.service;

import com.betrybe.agrix.controller.dto.FertilizerCreationDto;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.model.repositories.CropRepository;
import com.betrybe.agrix.model.repositories.FertilizerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service da classe Fertilizer.
 */
@Service
public class FertilizerService {
  private FertilizerRepository fertilizerRepository;
  private CropRepository cropRepository;

  /**
   * Construtor da classe.
   */
  @Autowired
  public FertilizerService(
      FertilizerRepository fertilizerRepository,
      CropRepository cropRepository
  ) {
    this.fertilizerRepository = fertilizerRepository;
    this.cropRepository = cropRepository;
  }

  /**
   * Método de criação de um fertilizer.
   */
  public Fertilizer saveFertilizer(FertilizerCreationDto fertilizer) {
    Fertilizer newFertilizer = new Fertilizer();

    newFertilizer.setName(fertilizer.name());
    newFertilizer.setBrand(fertilizer.brand());
    newFertilizer.setComposition(fertilizer.composition());

    return this.fertilizerRepository.save(newFertilizer);
  }

  /**
   * Método que retorna todos os fertilizantes.
   */
  public List<Fertilizer> getAllFertilizers() {
    return this.fertilizerRepository.findAll();
  }

  /**
   * Método que retorna um fertilizante baseado no id, caso exista.
   */
  public Optional<Fertilizer> getFertilizerById(Long id) {
    return this.fertilizerRepository.findById(id);
  }

}
