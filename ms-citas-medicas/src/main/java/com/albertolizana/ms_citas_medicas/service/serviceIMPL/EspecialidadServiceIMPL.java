package com.albertolizana.ms_citas_medicas.service.serviceIMPL;

import java.util.List;

import org.springframework.stereotype.Service;

import com.albertolizana.ms_citas_medicas.dto.EspecialidadResponseDTO;
import com.albertolizana.ms_citas_medicas.exception.ResourceNotFoundException;
import com.albertolizana.ms_citas_medicas.repository.EspecialidadRepository;
import com.albertolizana.ms_citas_medicas.service.EspecialidadService;

@Service
public class EspecialidadServiceIMPL implements EspecialidadService{

    private final EspecialidadRepository especialidadRepository;

    public EspecialidadServiceIMPL(EspecialidadRepository especialidadRepository){
        this.especialidadRepository = especialidadRepository;
    }

    @Override
    public List<EspecialidadResponseDTO> getAllEspecialidades() {
        return especialidadRepository.findAll()
                                .stream()
                                .map(p -> EspecialidadResponseDTO
                                    .builder()
                                    .idEspecialidad(p.getIdEspecialidad())
                                    .nombreEspecialidad(p.getNombre())
                                    .build())
                                .toList();
    }

    @Override
    public EspecialidadResponseDTO getEspecialidad(Long id) {
        return especialidadRepository.findById(id)
                                .map(p -> EspecialidadResponseDTO
                                    .builder()
                                    .idEspecialidad(p.getIdEspecialidad())
                                    .nombreEspecialidad(p.getNombre())
                                    .build())
                                .orElseThrow(() -> new ResourceNotFoundException("Especialidad con id " + id + " no encontrada"));
    }
}
