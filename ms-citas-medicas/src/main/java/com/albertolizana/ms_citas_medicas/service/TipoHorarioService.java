package com.albertolizana.ms_citas_medicas.service;

import java.util.List;

import com.albertolizana.ms_citas_medicas.dto.TipoHorarioResponseDTO;

public interface TipoHorarioService {

    public List<TipoHorarioResponseDTO> getAllTipoHorario();
    public TipoHorarioResponseDTO getTipoHorario(Long id);
    
}
