package com.albertolizana.ms_citas_medicas.service;
import java.util.List;

import com.albertolizana.ms_citas_medicas.dto.EspecialidadResponseDTO;

public interface EspecialidadService {
    
    public List<EspecialidadResponseDTO> getAllEspecialidades();
    public EspecialidadResponseDTO getEspecialidad(Long id);

}
