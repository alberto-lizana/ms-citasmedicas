package com.albertolizana.ms_citas_medicas.service.serviceIMPL;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.albertolizana.ms_citas_medicas.dto.CrearHorariosRequestDTO;
import com.albertolizana.ms_citas_medicas.dto.EspecialidadResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.HorarioMedicoResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.MedicoResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.PlantillaHorarioResponseDTO;
import com.albertolizana.ms_citas_medicas.exception.ResourceNotFoundException;
import com.albertolizana.ms_citas_medicas.model.HorarioMedico;
import com.albertolizana.ms_citas_medicas.model.Medico;
import com.albertolizana.ms_citas_medicas.model.PlantillaHorario;
import com.albertolizana.ms_citas_medicas.repository.HorarioMedicoRepository;
import com.albertolizana.ms_citas_medicas.repository.MedicoRepository;
import com.albertolizana.ms_citas_medicas.repository.PlantillaHorarioRepository;
import com.albertolizana.ms_citas_medicas.service.HorarioMedicoService;
import com.albertolizana.ms_citas_medicas.service.SlotHorarioService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HorarioMedicoServiceIMPL implements HorarioMedicoService {

    private final HorarioMedicoRepository horarioMedicoRepository;
    private final MedicoRepository medicoRepository;
    private final PlantillaHorarioRepository plantillaHorarioRepository;
    private final SlotHorarioService slotHorarioService;

    public HorarioMedicoServiceIMPL(HorarioMedicoRepository horarioMedicoRepository, MedicoRepository medicoRepository,
                                    PlantillaHorarioRepository plantillaHorarioRepository, SlotHorarioService slotHorarioService)
    {
        this.horarioMedicoRepository = horarioMedicoRepository;
        this.medicoRepository = medicoRepository;
        this.plantillaHorarioRepository = plantillaHorarioRepository;
        this.slotHorarioService = slotHorarioService;
    }

    @Override
    public List<HorarioMedicoResponseDTO> getAllHorariosMedico() {
        return horarioMedicoRepository.findAll()
                                    .stream()
                                    .map(p -> HorarioMedicoResponseDTO
                                        .builder()
                                        .idHorarioMedico(p.getIdHorarioMedico())
                                        .fecha(p.getFecha())
                                        
                                        .medico(MedicoResponseDTO
                                            .builder()
                                            .idMedico(p.getMedico().getIdMedico())
                                            .nombre(p.getMedico().getNombre())
                                            
                                            .especialidad(EspecialidadResponseDTO
                                                .builder()
                                                .idEspecialidad(p.getMedico().getEspecialidad().getIdEspecialidad())
                                                .nombreEspecialidad(p.getMedico().getEspecialidad().getNombre())
                                            .build())
                                        .build())

                                        .plantillaHorario(PlantillaHorarioResponseDTO
                                            .builder()
                                            .idPlantilla(p.getPlantillaHorario().getIdPlantillaHorario())
                                            .duracionMinutosPlantilla(p.getPlantillaHorario().getDuracionMinutos())
                                            .nombreTipoHorario(p.getPlantillaHorario().getTipoHorario().getNombre())
                                        .build())
                                    .build())
                                    .toList();
        }

    @Override
    public HorarioMedicoResponseDTO getHorarioMedico(Long id) {
        return horarioMedicoRepository.findById(id)
                                    .map(p -> HorarioMedicoResponseDTO
                                        .builder()
                                        .idHorarioMedico(p.getIdHorarioMedico())
                                        .fecha(p.getFecha())
                                        
                                        .medico(MedicoResponseDTO
                                            .builder()
                                            .idMedico(p.getMedico().getIdMedico())
                                            .nombre(p.getMedico().getNombre())

                                            .especialidad(EspecialidadResponseDTO
                                                .builder()
                                                .idEspecialidad(p.getMedico().getEspecialidad().getIdEspecialidad())
                                                .nombreEspecialidad(p.getMedico().getEspecialidad().getNombre())
                                            .build())
                                        .build())

                                        .plantillaHorario(PlantillaHorarioResponseDTO
                                            .builder()
                                            .idPlantilla(p.getPlantillaHorario().getIdPlantillaHorario())
                                            .duracionMinutosPlantilla(p.getPlantillaHorario().getDuracionMinutos())
                                            .nombreTipoHorario(p.getPlantillaHorario().getTipoHorario().getNombre())
                                        .build())
                                    .build())
                                    .orElseThrow(() -> new ResourceNotFoundException("Horario del médico con id " + id + " no encontrado"));
    }
   
    @Override
    public void crearHorarios(CrearHorariosRequestDTO dto) {

        if (dto.getFechaFin().isBefore(dto.getFechaInicio()) || dto.getFechaFin().isEqual(dto.getFechaInicio())) {
            throw new RuntimeException("La fecha de fin debe ser posterior a la fecha de inicio");
        }

        List<Medico> medicos = medicoRepository.findAll();
        List<PlantillaHorario> plantillas = plantillaHorarioRepository.findAll();

        LocalDate fechaActual = dto.getFechaInicio();

        while (!fechaActual.isAfter(dto.getFechaFin())) {

            int index = 0; 

            for (Medico m : medicos) {

                PlantillaHorario p = plantillas.get(index % plantillas.size());

                if (!horarioMedicoRepository.existsByMedicoAndFechaAndPlantillaHorario(m, fechaActual, p)) {

                    HorarioMedico hm = HorarioMedico.builder()
                                                .fecha(fechaActual)
                                                .plantillaHorario(p)
                                                .medico(m)
                                                .build();

                    horarioMedicoRepository.save(hm);
                    slotHorarioService.generarSlotsParaHorario(hm);
                }
                index++;
            }
            fechaActual = fechaActual.plusDays(1);
        }
    }
}
