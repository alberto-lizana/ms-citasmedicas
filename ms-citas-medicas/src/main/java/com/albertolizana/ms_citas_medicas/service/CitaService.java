package com.albertolizana.ms_citas_medicas.service;

import java.util.List;

import com.albertolizana.ms_citas_medicas.dto.CitaResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.CrearReservaCitaRequestDTO;
import com.albertolizana.ms_citas_medicas.dto.CrearReservaCitaResponseDTO;

public interface CitaService {

    public List<CitaResponseDTO> getAllCitas();
    public CitaResponseDTO getCita(Long id);
    public List<CitaResponseDTO> getCitasPorEstado(String nombre);
    public CrearReservaCitaResponseDTO crearReservaCita(CrearReservaCitaRequestDTO dto);
    public void borrarCita(Long id);
}
