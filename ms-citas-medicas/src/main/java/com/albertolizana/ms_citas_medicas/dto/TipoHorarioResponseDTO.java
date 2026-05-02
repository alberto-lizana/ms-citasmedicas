package com.albertolizana.ms_citas_medicas.dto;

import java.time.LocalTime;

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
public class TipoHorarioResponseDTO extends RepresentationModel<TipoHorarioResponseDTO> {

    private Long idTipoHorario;
    private String nombre;
    private LocalTime horaInicio;
    private LocalTime horaFin;

}
