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

import org.springframework.transaction.annotation.Transactional;

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
        // Se valida que fecha fin sea mayor a fecha inicio
        if (dto.getFechaFin().isBefore(dto.getFechaInicio()) || dto.getFechaFin().isEqual(dto.getFechaInicio())) {
            throw new RuntimeException("La fecha de fin debe ser posterior a la fecha de inicio");
        }
        // Obtenemos Todos Los medicos
        List<Medico> medicos = medicoRepository.findAll();
        // Obtenemos Todas Las plantillas
        List<PlantillaHorario> plantillas = plantillaHorarioRepository.findAll();

        LocalDate fechaActual = dto.getFechaInicio();

        // Mientras fecha inicio no este después de fecha fin continuará
        while (!fechaActual.isAfter(dto.getFechaFin())) {

            int index = 0; 

            for (Medico m : medicos) {
                // Obtenemos la plantilla el módulo nos asegura que él índice nunca se salga del rango de la lista.
                PlantillaHorario p = plantillas.get(index % plantillas.size());

                // Aqui a pesar de tener la constraint volvemos a verifiicar antes de crear el horario-medico
                if (!horarioMedicoRepository.existsByMedicoAndFechaAndPlantillaHorario(m, fechaActual, p)) {

                    HorarioMedico hm = HorarioMedico.builder()
                                                .fecha(fechaActual)
                                                .plantillaHorario(p)
                                                .medico(m)
                                                .build();

                    horarioMedicoRepository.save(hm); // Se guarda el horario medico
                    slotHorarioService.generarSlotsParaHorario(hm); // Mandamos a crear Slots. 
                }
                index++;
            }
            fechaActual = fechaActual.plusDays(1);
        }
    }
}
