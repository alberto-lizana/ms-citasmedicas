package com.albertolizana.ms_citas_medicas.service;

import java.util.List;

import com.albertolizana.ms_citas_medicas.dto.MedicoResponseDTO;

public interface MedicoService {

    public List<MedicoResponseDTO> getAllMedicos();
    public MedicoResponseDTO getMedico(Long id);

}
