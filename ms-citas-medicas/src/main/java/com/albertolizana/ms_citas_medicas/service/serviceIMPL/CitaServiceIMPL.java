package com.albertolizana.ms_citas_medicas.service.serviceIMPL;

import java.util.List;

import org.springframework.stereotype.Service;

import com.albertolizana.ms_citas_medicas.dto.CitaResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.CrearReservaCitaRequestDTO;
import com.albertolizana.ms_citas_medicas.dto.CrearReservaCitaResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.EstadoCitaResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.HorarioMedicoResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.PacienteResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.SlotHorarioResponseDTO;
import com.albertolizana.ms_citas_medicas.exception.ResourceNotFoundException;
import com.albertolizana.ms_citas_medicas.model.Cita;
import com.albertolizana.ms_citas_medicas.model.Paciente;
import com.albertolizana.ms_citas_medicas.model.SlotHorario;
import com.albertolizana.ms_citas_medicas.repository.CitaRepository;
import com.albertolizana.ms_citas_medicas.repository.EstadoCitaRepository;
import com.albertolizana.ms_citas_medicas.repository.PacienteRepository;
import com.albertolizana.ms_citas_medicas.repository.SlotHorarioRepository;
import com.albertolizana.ms_citas_medicas.service.CitaService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CitaServiceIMPL implements CitaService {

    private final CitaRepository citaRepository;
    private final SlotHorarioRepository slotHorarioRepository;
    private final PacienteRepository pacienteRepository;
    private final EstadoCitaRepository estadoCitaRepository;


    public CitaServiceIMPL(CitaRepository citaRepository,SlotHorarioRepository slotHorarioRepository,
                           PacienteRepository pacienteRepository, EstadoCitaRepository estadoCitaRepository)
    {
        this.citaRepository = citaRepository;
        this.slotHorarioRepository = slotHorarioRepository;
        this.pacienteRepository = pacienteRepository;
        this.estadoCitaRepository = estadoCitaRepository;
    }

    @Override
    public List<CitaResponseDTO> getAllCitas() {
        return citaRepository.findAllWithRelations()
                        .stream()
                        .map(p -> CitaResponseDTO
                            .builder().idCita(p.getIdCita())
                            .idSlot(p.getSlotHorario().getIdSlotHorario())
                            
                            .estadoCita(EstadoCitaResponseDTO
                                .builder()
                                .idEstadoCita(p.getEstadoCita().getIdEstadoCita())
                                .nombre(p.getEstadoCita().getNombre())
                            .build())
                            
                            .paciente(PacienteResponseDTO
                                .builder()
                                .idPaciente(p.getPaciente().getIdPaciente())
                                .nombre(p.getPaciente().getNombre())
                                .email(p.getPaciente().getEmail())
                                .estado(p.getPaciente().getEstado())
                            .build())
                        .build())
                        .toList();
    }

    @Override
    public CitaResponseDTO getCita(Long id) {
        return citaRepository.findWithRelations(id)
                        .map(p -> CitaResponseDTO
                            .builder().idCita(p.getIdCita())
                            .idSlot(p.getSlotHorario().getIdSlotHorario())
                            
                            .estadoCita(EstadoCitaResponseDTO
                                .builder()
                                .idEstadoCita(p.getEstadoCita().getIdEstadoCita())
                                .nombre(p.getEstadoCita().getNombre())
                            .build())
                            
                            .paciente(PacienteResponseDTO
                                .builder()
                                .idPaciente(p.getPaciente().getIdPaciente())
                                .nombre(p.getPaciente().getNombre())
                                .email(p.getPaciente().getEmail())
                                .estado(p.getPaciente().getEstado())
                            .build())
                        .build())
                        .orElseThrow(() -> new ResourceNotFoundException("La cita con id " + id + " no ha sido encontrada"));
    }

    @Override
    public List<CitaResponseDTO> getCitasPorEstado(String nombreEstado) {
        nombreEstado = nombreEstado.toUpperCase().trim();

        return citaRepository.findByEstadoCitaNombre(nombreEstado)
                        .stream()
                        .map(p -> CitaResponseDTO
                            .builder().idCita(p.getIdCita())
                            .idSlot(p.getSlotHorario().getIdSlotHorario())
                            
                            .estadoCita(EstadoCitaResponseDTO
                                .builder()
                                .idEstadoCita(p.getEstadoCita().getIdEstadoCita())
                                .nombre(p.getEstadoCita().getNombre())
                            .build())
                            
                            .paciente(PacienteResponseDTO
                                .builder()
                                .idPaciente(p.getPaciente().getIdPaciente())
                                .nombre(p.getPaciente().getNombre())
                                .email(p.getPaciente().getEmail())
                                .estado(p.getPaciente().getEstado())
                            .build())
                        .build())
                        .toList();
    }

    @Override
    public CrearReservaCitaResponseDTO crearReservaCita(CrearReservaCitaRequestDTO dto) {
        
        SlotHorario slot = obtenerSlotPorId(dto.getIdSlotHorario());
        
        if (slot.getCita() != null) {
            throw new RuntimeException("El slot ya está ocupado");
        } 
        
        Paciente paciente = obtenerPacientePorId(dto.getIdPaciente());

        Cita crearCita = Cita.builder()
                        .slotHorario(slot)
                        .estadoCita(estadoCitaRepository.findByNombre("CONFIRMADA"))
                        .paciente(paciente)
                        .build();
        
        Cita citaGuardada = citaRepository.save(crearCita);


        return CrearReservaCitaResponseDTO.builder()
                                        .idCita(citaGuardada.getIdCita())
                                        .slotHorario(SlotHorarioResponseDTO
                                            .builder()
                                            .idSlotHorario(citaGuardada.getSlotHorario().getIdSlotHorario())
                                            .horaInicio(citaGuardada.getSlotHorario().getHoraInicio())
                                            .horaFin(citaGuardada.getSlotHorario().getHoraFin())
                                                .horarioMedico(HorarioMedicoResponseDTO
                                                    .builder()
                                                    .idHorarioMedico(citaGuardada.getSlotHorario().getHorarioMedico().getIdHorarioMedico())
                                                    .fecha(citaGuardada.getSlotHorario().getHorarioMedico().getFecha())
                                                .build())
                                            .build())
                                        .estadoCita(EstadoCitaResponseDTO
                                            .builder()
                                            .idEstadoCita(citaGuardada.getEstadoCita().getIdEstadoCita())
                                            .nombre(citaGuardada.getEstadoCita().getNombre()).build())
                                        .paciente(PacienteResponseDTO
                                            .builder()
                                            .idPaciente(citaGuardada.getPaciente().getIdPaciente())
                                            .nombre(citaGuardada.getPaciente().getNombre())
                                            .email(citaGuardada.getPaciente().getEmail()).build())
                                        .build();
    }

    public SlotHorario obtenerSlotPorId(Long id){
        return slotHorarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Slot con id " + id + " no encontrado"));
    }


    public Paciente obtenerPacientePorId(Long id){
        return pacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Paciente con id " + id + " no encontrado"));
    }    
}
