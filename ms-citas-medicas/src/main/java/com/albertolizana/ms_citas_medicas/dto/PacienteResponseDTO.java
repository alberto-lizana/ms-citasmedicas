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
public class PacienteResponseDTO extends RepresentationModel<PacienteResponseDTO> {

    private Long idPaciente;
    private String nombre;
    private String email;
    private Boolean estado;
    
}

