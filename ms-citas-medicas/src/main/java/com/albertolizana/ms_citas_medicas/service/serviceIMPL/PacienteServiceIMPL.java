package com.albertolizana.ms_citas_medicas.service.serviceIMPL;

import java.util.List;

import org.springframework.stereotype.Service;

import com.albertolizana.ms_citas_medicas.dto.PacienteResponseDTO;
import com.albertolizana.ms_citas_medicas.exception.ResourceNotFoundException;
import com.albertolizana.ms_citas_medicas.model.Paciente;
import com.albertolizana.ms_citas_medicas.repository.PacienteRepository;
import com.albertolizana.ms_citas_medicas.service.PacienteService;

@Service
public class PacienteServiceIMPL implements PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceIMPL(PacienteRepository pacienteRepository){
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public List<PacienteResponseDTO> getAllPacientes() {
        return pacienteRepository.findAll()
                            .stream()
                            .map(p -> PacienteResponseDTO
                                .builder()
                                .idPaciente(p.getIdPaciente())
                                .nombre(p.getNombre())
                                .email(p.getEmail())
                                .estado(p.getEstado())
                                .build())
                            .toList();
    }

    @Override
    public PacienteResponseDTO getPaciente(Long id) {
        return pacienteRepository.findById(id)
                            .map(p -> PacienteResponseDTO
                                .builder()
                                .idPaciente(p.getIdPaciente())
                                .nombre(p.getNombre())
                                .email(p.getEmail())
                                .estado(p.getEstado())
                                .build())
                            .orElseThrow(() -> new ResourceNotFoundException("Paciente con id " + id + " no encontrado"));
    }

    @Override
    public List<PacienteResponseDTO> getPacientesPorEstado(boolean estado){
        return pacienteRepository.findByEstado(estado)
                            .stream()
                            .map(p -> PacienteResponseDTO
                                .builder()
                                .idPaciente(p.getIdPaciente())
                                .nombre(p.getNombre())
                                .email(p.getEmail())
                                .estado(p.getEstado())
                                .build())
                            .toList();
    }


    @Override
    public void borrarPacienteFisico(Long id) {
        if (!pacienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Paciente con id " + id + " no encontrado");
        }
        pacienteRepository.deleteById(id);
    }


    @Override
    public void borrarPacienteLogico(Long id) {
        Paciente paciente = pacienteRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Paciente con id " + id + " no encontrado"));
        
        paciente.setEstado(false);
        pacienteRepository.save(paciente);
    }   
    
    
}
