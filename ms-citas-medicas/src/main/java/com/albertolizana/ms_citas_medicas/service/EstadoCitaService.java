package com.albertolizana.ms_citas_medicas.service;

import java.util.List;

import com.albertolizana.ms_citas_medicas.dto.EstadoCitaResponseDTO;

public interface EstadoCitaService {

    public List<EstadoCitaResponseDTO> getAllEstadoCita();
    public EstadoCitaResponseDTO getEstadoCita(Long id);
}
