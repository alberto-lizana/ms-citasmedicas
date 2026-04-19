
package com.albertolizana.ms_citas_medicas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CrearReservaCitaRequestDTO {

    @NotNull(message = "El id del slot es obligatorio")
    private Long idSlotHorario;

    @NotNull(message = "El id del paciente es obligatorio")
    private Long idPaciente;    

}
