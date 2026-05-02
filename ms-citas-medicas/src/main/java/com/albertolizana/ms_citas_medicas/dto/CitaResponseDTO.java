package com.albertolizana.ms_citas_medicas.dto;

import org.springframework.hateoas.RepresentationModel;

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
public class CitaResponseDTO extends RepresentationModel<CitaResponseDTO> {

    private Long idCita;
    private Long idSlot;
    private SlotHorarioResponseDTO slotHorario;
    private EstadoCitaResponseDTO estadoCita;
    private PacienteResponseDTO paciente; 

}
