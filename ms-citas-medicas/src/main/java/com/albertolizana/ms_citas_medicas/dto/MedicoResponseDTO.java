package com.albertolizana.ms_citas_medicas.dto;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class MedicoResponseDTO extends RepresentationModel<MedicoResponseDTO> {

    private Long idMedico;
    private String nombre;
    private EspecialidadResponseDTO especialidad;  

}
