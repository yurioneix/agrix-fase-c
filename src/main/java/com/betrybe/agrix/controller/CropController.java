package com.betrybe.agrix.controller;

import com.betrybe.agrix.controller.dto.CropResponseDto;
import com.betrybe.agrix.controller.exception.CropNotFoundException;
import com.betrybe.agrix.controller.exception.FertilizerNotFoundException;
import com.betrybe.agrix.model.entities.Crop;
import com.betrybe.agrix.model.entities.Fertilizer;
import com.betrybe.agrix.service.CropService;
import com.betrybe.agrix.service.FertilizerService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller de Crop.
 */
@RestController
@RequestMapping(value = "/crops")
public class CropController {
  private final CropService cropService;
  private final FertilizerService fertilizerService;

  @Autowired
  public CropController(CropService cropService, FertilizerService fertilizerService) {
    this.cropService = cropService;
    this.fertilizerService = fertilizerService;
  }

  /**
   * Rota GET /crops que retorna todas as plantações.
   */
  @GetMapping()
  @Secured({"MANAGER", "ADMIN"})
  public List<CropResponseDto> getAllCrops() {
    List<Crop> crops = cropService.getAllCrops();

    return crops.stream()
        .map((crop) -> new CropResponseDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            crop.getFarm().getId()
        )).collect(Collectors.toList());
  }

  /**
   * Rota GET /crops/{id}/ que retorna uma plantação pelo seu id.
   */
  @GetMapping("/{id}")
  public ResponseEntity<CropResponseDto> getCropById(@PathVariable Long id) {
    Optional<Crop> optionalCrop = cropService.getCropById(id);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }

    Crop crop = optionalCrop.get();

    CropResponseDto cropResponseDto = new CropResponseDto(
        crop.getId(),
        crop.getName(),
        crop.getPlantedArea(),
        crop.getPlantedDate(),
        crop.getHarvestDate(),
        crop.getFarm().getId()
    );

    return ResponseEntity.ok().body(cropResponseDto);
  }

  /**
   * Rota GET /crops/{cropId}/fertilizers.
   */
  @GetMapping("{cropId}/fertilizers")
  public ResponseEntity<List<Fertilizer>> getFertilizersByCropId(@PathVariable Long cropId) {
    Optional<Crop> optionalCrop = this.cropService.getCropById(cropId);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }

    return ResponseEntity.status(HttpStatus.OK).body(optionalCrop.get().getFertilizers());
  }

  /**
   * Método GET /crops/search/start&end.
   */
  @GetMapping("/search")
  public ResponseEntity<List<CropResponseDto>> getCropByHarvestDate(
      @RequestParam("start") LocalDate start,
      @RequestParam("end") LocalDate end
  ) {
    Optional<List<Crop>> crops = cropService.getCropByHarvestDate(start, end);

    if (crops.isEmpty()) {
      throw new CropNotFoundException();
    }

    List<Crop> getCrops = crops.get();

    List<CropResponseDto> responseCrops = getCrops.stream()
        .map((crop) -> new CropResponseDto(
            crop.getId(),
            crop.getName(),
            crop.getPlantedArea(),
            crop.getPlantedDate(),
            crop.getHarvestDate(),
            crop.getFarm().getId()
        )).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(responseCrops);
  }

  /**
   * Rota POST /crops/{cropId}/fertilizers/{fertilizerId}.
   */
  @PostMapping("{cropId}/fertilizers/{fertilizerId}")
  public ResponseEntity<String> associateCropWithFertilizer(
      @PathVariable Long cropId,
      @PathVariable Long fertilizerId
  ) {
    Optional<Crop> optionalCrop = this.cropService.getCropById(cropId);

    Optional<Fertilizer> optionalFertilizer =
        this.fertilizerService.getFertilizerById(fertilizerId);

    if (optionalCrop.isEmpty()) {
      throw new CropNotFoundException();
    }

    if (optionalFertilizer.isEmpty()) {
      throw new FertilizerNotFoundException();
    }

    this.cropService.associateCropWithFertilizer(
        optionalCrop.get(),
        optionalFertilizer.get()
    );

    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body("Fertilizante e plantação associados com sucesso!");
  }
}
