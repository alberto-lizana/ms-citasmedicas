package com.albertolizana.ms_citas_medicas.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
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
public class CrearHorariosRequestDTO {

    @NotNull(message = "La fecha inicial es obligatoria")
    @FutureOrPresent(message = "La fecha de inicio debe ser hoy o en el futuro")
    private LocalDate fechaInicio;

    @NotNull(message = "La fecha de termino es obligatoria")
    @Future(message = "La fecha de fin debe ser mayor a hoy")
    private LocalDate fechaFin;

}
