package com.albertolizana.ms_citas_medicas.dto;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PlantillaHorarioResponseDTO extends RepresentationModel<PlantillaHorarioResponseDTO> {

    private Long idPlantilla;
    private int duracionMinutosPlantilla;    
    private String nombreTipoHorario;
    
}
