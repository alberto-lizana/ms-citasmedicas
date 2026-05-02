package com.albertolizana.ms_citas_medicas.dto;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EstadoCitaResponseDTO extends RepresentationModel<EstadoCitaResponseDTO> {

    private Long idEstadoCita;
    private String nombre;

}
