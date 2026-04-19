package com.albertolizana.ms_citas_medicas.service;

import java.util.List;

import com.albertolizana.ms_citas_medicas.dto.CrearHorariosRequestDTO;
import com.albertolizana.ms_citas_medicas.dto.HorarioMedicoResponseDTO;

public interface HorarioMedicoService {

    public List<HorarioMedicoResponseDTO> getAllHorariosMedico();
    public HorarioMedicoResponseDTO getHorarioMedico(Long id);
    public void crearHorarios(CrearHorariosRequestDTO dto);

}
