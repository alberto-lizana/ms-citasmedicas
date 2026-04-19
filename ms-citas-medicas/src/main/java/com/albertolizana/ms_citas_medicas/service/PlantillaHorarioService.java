package com.albertolizana.ms_citas_medicas.service;

import java.util.List;

import com.albertolizana.ms_citas_medicas.dto.PlantillaHorarioResponseDTO;

public interface PlantillaHorarioService {
    public List<PlantillaHorarioResponseDTO> getAllPlantillaHorario();
    public PlantillaHorarioResponseDTO getPlantillaHorario(Long id);
}
