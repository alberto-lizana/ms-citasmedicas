package com.albertolizana.ms_citas_medicas.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class GenerarSlotsRequestDTO {

    @Positive(message = "Debe ser mayor a 0")
    @Min(value = 1, message = "El número de días debe ser al menos 1")
    @Max(value = 120, message = "El número de días no puede ser mayor a 30")
    private int dias;

}
