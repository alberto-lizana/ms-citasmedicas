package com.albertolizana.ms_citas_medicas.service.serviceIMPL;

import java.util.List;

import org.springframework.stereotype.Service;

import com.albertolizana.ms_citas_medicas.dto.PlantillaHorarioResponseDTO;
import com.albertolizana.ms_citas_medicas.exception.ResourceNotFoundException;
import com.albertolizana.ms_citas_medicas.repository.PlantillaHorarioRepository;
import com.albertolizana.ms_citas_medicas.service.PlantillaHorarioService;

@Service
public class PlantillaHorarioServiceIMPL implements PlantillaHorarioService {

    private final PlantillaHorarioRepository plantillaHorarioRepository;

    public PlantillaHorarioServiceIMPL(PlantillaHorarioRepository plantillaHorarioRepository){
        this.plantillaHorarioRepository = plantillaHorarioRepository;
    }

    @Override
    public List<PlantillaHorarioResponseDTO> getAllPlantillaHorario() {
        return plantillaHorarioRepository.findAll()
                                    .stream()
                                    .map(p -> PlantillaHorarioResponseDTO
                                        .builder()
                                        .idPlantilla(p.getIdPlantillaHorario())
                                        .nombreTipoHorario(p.getTipoHorario().getNombre())
                                        .duracionMinutosPlantilla(p.getDuracionMinutos())
                                        .build())
                                    .toList();
    }

    @Override
    public PlantillaHorarioResponseDTO getPlantillaHorario(Long id) {
        return plantillaHorarioRepository.findById(id)
                                    .map(p -> PlantillaHorarioResponseDTO                                        
                                        .builder()
                                        .idPlantilla(p.getIdPlantillaHorario())
                                        .nombreTipoHorario(p.getTipoHorario().getNombre())
                                        .duracionMinutosPlantilla(p.getDuracionMinutos())
                                        .build())
                                    .orElseThrow(() -> new ResourceNotFoundException("Plantilla Horario con id " + id + " no ha sido encontrada"));
    }
}
