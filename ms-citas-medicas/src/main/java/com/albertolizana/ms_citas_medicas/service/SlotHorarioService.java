package com.albertolizana.ms_citas_medicas.service;

import java.util.List;

import com.albertolizana.ms_citas_medicas.dto.SlotHorarioResponseDTO;
import com.albertolizana.ms_citas_medicas.model.HorarioMedico;

public interface SlotHorarioService {

    public List<SlotHorarioResponseDTO> getAllSlots();
    public SlotHorarioResponseDTO getSlot(Long id);
    public void generarSlotsParaHorario(HorarioMedico h);
    public List<SlotHorarioResponseDTO> getAllSlotsByCita(boolean disponible);
}
