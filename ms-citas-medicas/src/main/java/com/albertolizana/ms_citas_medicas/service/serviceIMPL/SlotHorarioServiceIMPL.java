package com.albertolizana.ms_citas_medicas.service.serviceIMPL;

import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.albertolizana.ms_citas_medicas.dto.CitaResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.EspecialidadResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.EstadoCitaResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.HorarioMedicoResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.MedicoResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.PacienteResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.SlotHorarioResponseDTO;
import com.albertolizana.ms_citas_medicas.exception.ResourceNotFoundException;
import com.albertolizana.ms_citas_medicas.model.HorarioMedico;
import com.albertolizana.ms_citas_medicas.model.SlotHorario;
import com.albertolizana.ms_citas_medicas.repository.SlotHorarioRepository;
import com.albertolizana.ms_citas_medicas.service.SlotHorarioService;

import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SlotHorarioServiceIMPL implements SlotHorarioService {

    private final SlotHorarioRepository slotHorarioRepository;

    public SlotHorarioServiceIMPL(SlotHorarioRepository slotHorarioRepository){
        this.slotHorarioRepository = slotHorarioRepository;
    }

    @Override
    public List<SlotHorarioResponseDTO> getAllSlots() {
        return slotHorarioRepository.findAll()
                                .stream()
                                .map(p -> SlotHorarioResponseDTO
                                        .builder()
                                        .idSlotHorario(p.getIdSlotHorario())
                                        .horaInicio(p.getHoraInicio())
                                        .horaFin(p.getHoraFin())

                                        .cita(p.getCita() != null ? CitaResponseDTO
                                            .builder()
                                            .idCita(p.getCita().getIdCita())
                                            .idSlot(p.getIdSlotHorario())

                                            .estadoCita(p.getCita().getEstadoCita() != null ? EstadoCitaResponseDTO
                                                .builder()
                                                .nombre(p.getCita().getEstadoCita().getNombre())
                                                .build() : null)

                                            .paciente(p.getCita().getPaciente() != null ? PacienteResponseDTO
                                                .builder()
                                                .nombre(p.getCita().getPaciente().getNombre())
                                                .email(p.getCita().getPaciente().getEmail())
                                                .estado(p.getCita().getPaciente().getEstado())
                                                .build() : null)  
                                            .build() : null)
                                        
                                            .horarioMedico(HorarioMedicoResponseDTO
                                            .builder()
                                            .fecha(p.getHorarioMedico().getFecha())
                                            
                                        .medico(MedicoResponseDTO
                                            .builder()
                                            .nombre(p.getHorarioMedico().getMedico().getNombre())
                                                .
                                            especialidad(EspecialidadResponseDTO
                                                .builder()
                                                .nombreEspecialidad(p.getHorarioMedico().getMedico().getEspecialidad().getNombre())
                                                .build())
                                            .build())
                                        .build())
                                    .build())
                                .toList();
}

    @Override
    public SlotHorarioResponseDTO getSlot(Long id) {
        return slotHorarioRepository.findById(id)
                                .map(p -> SlotHorarioResponseDTO
                                    .builder()
                                    .idSlotHorario(p.getIdSlotHorario()) 
                                    .horaInicio(p.getHoraInicio())
                                    .horaFin(p.getHoraFin())

                                    .cita(p.getCita() != null? CitaResponseDTO
                                        .builder()
                                        .idCita(p.getCita().getIdCita())
                                        .idSlot(p.getIdSlotHorario())

                                        .estadoCita(p.getCita().getEstadoCita() != null ? EstadoCitaResponseDTO
                                            .builder()
                                            .idEstadoCita(p.getCita().getEstadoCita().getIdEstadoCita())
                                            .nombre(p.getCita().getEstadoCita().getNombre())
                                            .build() : null)

                                        .paciente(p.getCita().getPaciente() != null ? PacienteResponseDTO
                                            .builder()
                                                .idPaciente(p.getCita().getPaciente().getIdPaciente())
                                                .nombre(p.getCita().getPaciente().getNombre())
                                                .email(p.getCita().getPaciente().getEmail())
                                                .estado(p.getCita().getPaciente().getEstado())
                                                .build() : null)
                                                .build() : null)
                                        
                                            .horarioMedico(HorarioMedicoResponseDTO
                                            .builder()
                                            .fecha(p.getHorarioMedico().getFecha())
                                            
                                        .medico(MedicoResponseDTO
                                            .builder()
                                            .nombre(p.getHorarioMedico().getMedico().getNombre())
                                                .
                                            especialidad(EspecialidadResponseDTO
                                                .builder()
                                                .nombreEspecialidad(p.getHorarioMedico().getMedico().getEspecialidad().getNombre())
                                                .build())
                                            .build())
                                        .build())
                                    .build())
                                .orElseThrow(() -> new ResourceNotFoundException("Slot de horario con id " + id + " no encontrado"));
    }

    @Override
    public void generarSlotsParaHorario(HorarioMedico h) {
        // verificamos si ya existen slots para el HorarioMedico enviado
        if (slotHorarioRepository.existsByHorarioMedico(h)) {
            return;
        }

        // Obtenemos la duración de una atención.
        int duracion = h.getPlantillaHorario().getDuracionMinutos();

        // Obtenemos la hora de entreda y de salida de la jornada laboral
        LocalTime inicio = h.getPlantillaHorario().getTipoHorario().getHoraInicio();
        LocalTime fin = h.getPlantillaHorario().getTipoHorario().getHoraFin();


        LocalTime horaActual = inicio;

        // Mientras la hora de inicio (actual) sea menor que la hora fin continuará
        while (horaActual.isBefore(fin)) {

            // Se agrega la duración para crear los slots posteriormente.
            LocalTime horaFin = horaActual.plusMinutes(duracion);

            if (horaFin.isAfter(fin)) {
                break;
            }

            SlotHorario slot = SlotHorario.builder()
                    .horaInicio(horaActual)
                    .horaFin(horaFin)
                    .horarioMedico(h)
                    .build();

            slotHorarioRepository.save(slot);

            // Al crear un nuevo slot tenemos que partir desde ese inicio.
            horaActual = horaFin;
        }
    }

    @Override
    public List<SlotHorarioResponseDTO> getAllSlotsByCita(boolean disponible) {
        if (disponible){
            return slotHorarioRepository.findByCitaIsNull()
                                    .stream()
                                    .map(p -> SlotHorarioResponseDTO
                                            .builder()
                                            .idSlotHorario(p.getIdSlotHorario())
                                            .horaInicio(p.getHoraInicio())
                                            .horaFin(p.getHoraFin())

                                            .cita(p.getCita() != null ? CitaResponseDTO
                                                .builder()
                                                .idCita(p.getCita().getIdCita())
                                                .idSlot(p.getIdSlotHorario())

                                                .estadoCita(p.getCita().getEstadoCita() != null ? EstadoCitaResponseDTO
                                                    .builder()
                                                    .nombre(p.getCita().getEstadoCita().getNombre())
                                                    .build() : null)

                                                .paciente(p.getCita().getPaciente() != null ? PacienteResponseDTO
                                                    .builder()
                                                    .nombre(p.getCita().getPaciente().getNombre())
                                                    .email(p.getCita().getPaciente().getEmail())
                                                    .estado(p.getCita().getPaciente().getEstado())
                                                    .build() : null)  
                                                .build() : null)
                                            
                                                .horarioMedico(HorarioMedicoResponseDTO
                                                .builder()
                                                .fecha(p.getHorarioMedico().getFecha())
                                                
                                            .medico(MedicoResponseDTO
                                                .builder()
                                                .nombre(p.getHorarioMedico().getMedico().getNombre())
                                                    .
                                                especialidad(EspecialidadResponseDTO
                                                    .builder()
                                                    .nombreEspecialidad(p.getHorarioMedico().getMedico().getEspecialidad().getNombre())
                                                    .build())
                                                .build())
                                            .build())
                                        .build())
                                    .toList();
        } else { return slotHorarioRepository.findByCitaIsNotNull()
                                        .stream()
                                        .map(p -> SlotHorarioResponseDTO
                                                .builder()
                                                .idSlotHorario(p.getIdSlotHorario())
                                                .horaInicio(p.getHoraInicio())
                                                .horaFin(p.getHoraFin())

                                                .cita(p.getCita() != null ? CitaResponseDTO
                                                    .builder()
                                                    .idCita(p.getCita().getIdCita())
                                                    .idSlot(p.getIdSlotHorario())

                                                    .estadoCita(p.getCita().getEstadoCita() != null ? EstadoCitaResponseDTO
                                                        .builder()
                                                        .nombre(p.getCita().getEstadoCita().getNombre())
                                                        .build() : null)

                                                    .paciente(p.getCita().getPaciente() != null ? PacienteResponseDTO
                                                        .builder()
                                                        .nombre(p.getCita().getPaciente().getNombre())
                                                        .email(p.getCita().getPaciente().getEmail())
                                                        .estado(p.getCita().getPaciente().getEstado())
                                                        .build() : null)  
                                                    .build() : null)
                                                
                                                    .horarioMedico(HorarioMedicoResponseDTO
                                                    .builder()
                                                    .fecha(p.getHorarioMedico().getFecha())
                                                    
                                                .medico(MedicoResponseDTO
                                                    .builder()
                                                    .nombre(p.getHorarioMedico().getMedico().getNombre())
                                                        .
                                                    especialidad(EspecialidadResponseDTO
                                                        .builder()
                                                        .nombreEspecialidad(p.getHorarioMedico().getMedico().getEspecialidad().getNombre())
                                                        .build())
                                                    .build())
                                                .build())
                                            .build())
                                        .toList();
        }
    }
}

