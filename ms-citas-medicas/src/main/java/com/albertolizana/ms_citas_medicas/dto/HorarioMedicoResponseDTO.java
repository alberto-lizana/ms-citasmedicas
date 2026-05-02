package com.albertolizana.ms_citas_medicas.dto;

import java.time.LocalDate;

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
public class HorarioMedicoResponseDTO extends RepresentationModel<HorarioMedicoResponseDTO>{

    private Long idHorarioMedico;
    private LocalDate fecha;
    private MedicoResponseDTO medico;
    private PlantillaHorarioResponseDTO plantillaHorario;
    
}
