package com.albertolizana.ms_citas_medicas.service.serviceIMPL;

import java.util.List;

import org.springframework.stereotype.Service;

import com.albertolizana.ms_citas_medicas.dto.EspecialidadResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.MedicoResponseDTO;
import com.albertolizana.ms_citas_medicas.exception.ResourceNotFoundException;
import com.albertolizana.ms_citas_medicas.repository.MedicoRepository;
import com.albertolizana.ms_citas_medicas.service.MedicoService;

@Service
public class MedicoServiceIMPL implements MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoServiceIMPL(MedicoRepository medicoRepository){
        this.medicoRepository = medicoRepository;
    }

    @Override
    public List<MedicoResponseDTO> getAllMedicos() {
        return medicoRepository.findAll()
                            .stream()
                            .map(p -> MedicoResponseDTO
                                .builder()
                                .idMedico(p.getIdMedico())
                                .nombre(p.getNombre())
                            
                            .especialidad(EspecialidadResponseDTO
                                .builder()
                                .idEspecialidad(p.getEspecialidad().getIdEspecialidad())
                                .nombreEspecialidad(p.getEspecialidad().getNombre())
                                .build())

                            .build())
                            .toList();
    }

    @Override
    public MedicoResponseDTO getMedico(Long id) {
        return medicoRepository.findById(id)
                            .map(p -> MedicoResponseDTO
                                .builder()
                                .idMedico(p.getIdMedico())
                                .nombre(p.getNombre())
                                
                                .especialidad(EspecialidadResponseDTO
                                    .builder()
                                    .idEspecialidad(p.getEspecialidad().getIdEspecialidad())
                                    .nombreEspecialidad(p.getEspecialidad().getNombre())
                                    .build())
                            .build())
                            .orElseThrow(() -> new ResourceNotFoundException("Medio con id " + id + " no encontrado"));
    }
}
