package com.albertolizana.ms_citas_medicas.service;

import java.util.List;

import com.albertolizana.ms_citas_medicas.dto.PacienteResponseDTO;

public interface PacienteService {

    public List<PacienteResponseDTO> getAllPacientes();
    public PacienteResponseDTO getPaciente(Long id);
    public void borrarPacienteFisico(Long id);
    public void borrarPacienteLogico(Long id);
}
