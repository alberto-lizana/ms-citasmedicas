package com.albertolizana.ms_citas_medicas.dto;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SlotHorarioResponseDTO {

    private Long idSlotHorario;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private CitaResponseDTO cita;
    private HorarioMedicoResponseDTO horarioMedico;

}
