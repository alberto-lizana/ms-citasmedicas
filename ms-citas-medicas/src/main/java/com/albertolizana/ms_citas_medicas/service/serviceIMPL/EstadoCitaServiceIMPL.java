package com.albertolizana.ms_citas_medicas.service.serviceIMPL;

import java.util.List;

import org.springframework.stereotype.Service;

import com.albertolizana.ms_citas_medicas.dto.EstadoCitaResponseDTO;
import com.albertolizana.ms_citas_medicas.exception.ResourceNotFoundException;
import com.albertolizana.ms_citas_medicas.repository.EstadoCitaRepository;
import com.albertolizana.ms_citas_medicas.service.EstadoCitaService;

@Service
public class EstadoCitaServiceIMPL implements EstadoCitaService {

    private final EstadoCitaRepository estadoCitaRepository;

    public EstadoCitaServiceIMPL(EstadoCitaRepository estadoCitaRepository){
        this.estadoCitaRepository = estadoCitaRepository;
    }

    @Override
    public List<EstadoCitaResponseDTO> getAllEstadoCita() {
        return estadoCitaRepository.findAll()
                                .stream()
                                .map(p -> EstadoCitaResponseDTO
                                    .builder()
                                    .idEstadoCita(p.getIdEstadoCita())
                                    .nombre(p.getNombre())
                                    .build())
                                .toList();
    }

    @Override
    public EstadoCitaResponseDTO getEstadoCita(Long id) {
        return estadoCitaRepository.findById(id)
                                .map(p -> EstadoCitaResponseDTO
                                    .builder()
                                    .idEstadoCita(p.getIdEstadoCita())
                                    .nombre(p.getNombre())
                                    .build())
                                .orElseThrow(() -> new ResourceNotFoundException("Estado con id " + id + " no encontrado"));
    }

}
