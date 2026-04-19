package com.albertolizana.ms_citas_medicas.service.serviceIMPL;

import java.util.List;

import org.springframework.stereotype.Service;

import com.albertolizana.ms_citas_medicas.dto.TipoHorarioResponseDTO;
import com.albertolizana.ms_citas_medicas.exception.ResourceNotFoundException;
import com.albertolizana.ms_citas_medicas.repository.TipoHorarioRepository;
import com.albertolizana.ms_citas_medicas.service.TipoHorarioService;

@Service
public class TipoHorarioServiceIMPL implements TipoHorarioService {

    private final TipoHorarioRepository tipoHorarioRepository;

    public TipoHorarioServiceIMPL(TipoHorarioRepository tipoHorarioRepository){
        this.tipoHorarioRepository = tipoHorarioRepository;
    }

    @Override
    public List<TipoHorarioResponseDTO> getAllTipoHorario() {
        return tipoHorarioRepository.findAll()
                                .stream()
                                .map(p -> TipoHorarioResponseDTO
                                    .builder()
                                    .idTipoHorario(p.getIdTipoHorario())
                                    .nombre(p.getNombre())
                                    .horaInicio(p.getHoraInicio())
                                    .horaFin(p.getHoraFin())
                                    .build())
                                .toList();
    }

    @Override
    public TipoHorarioResponseDTO getTipoHorario(Long id) {
        return tipoHorarioRepository.findById(id)
                                .map(p -> TipoHorarioResponseDTO
                                    .builder()
                                    .idTipoHorario(p.getIdTipoHorario())
                                    .nombre(p.getNombre())
                                    .horaInicio(p.getHoraInicio())
                                    .horaFin(p.getHoraFin())
                                    .build())
                                .orElseThrow(() -> new ResourceNotFoundException("Tipo fr horario con id " + id + " no encontrado"));
    }
}
