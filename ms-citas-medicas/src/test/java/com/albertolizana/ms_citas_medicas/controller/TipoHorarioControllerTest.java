package com.albertolizana.ms_citas_medicas.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.albertolizana.ms_citas_medicas.dto.TipoHorarioResponseDTO;
import com.albertolizana.ms_citas_medicas.service.TipoHorarioService;

@WebMvcTest(TipoHorarioController.class)
@DisplayName("Pruebas de Integridad - TipoHorarioController")
public class TipoHorarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TipoHorarioService tipoHorarioService;

    private List<TipoHorarioResponseDTO> tipoHorario;

    private TipoHorarioResponseDTO am;
    private TipoHorarioResponseDTO pm;

    @BeforeEach
    void setUp(){
        LocalTime horaInicioAm = LocalTime.of(7, 0);
        LocalTime horaFinAm = LocalTime.of(15, 0);

        LocalTime horaInicioPm = LocalTime.of(15, 0);
        LocalTime horaFinPm = LocalTime.of(23, 0);

        am = TipoHorarioResponseDTO.builder()
                .idTipoHorario(1L)
                .nombre("AM")
                .horaInicio(horaInicioAm)
                .horaFin(horaFinAm)
                .build();

        pm = TipoHorarioResponseDTO.builder()
                .idTipoHorario(2L)
                .nombre("PM")
                .horaInicio(horaInicioPm)
                .horaFin(horaFinPm)
                .build();

        tipoHorario = new ArrayList<>();
        tipoHorario.add(am);
        tipoHorario.add(pm);
    }

    @Test
    @DisplayName("GET /tipo-horario/all - Debe retornar una lista de tipo de horario")
    void getAllTipoHorario_ObtenerTodosLosTiposDeHorarios() throws Exception {
        
        when(tipoHorarioService.getAllTipoHorario()).thenReturn(tipoHorario);

        mockMvc.perform(get("/tipo-horario/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
            
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(2)))

                .andExpect(jsonPath("$[0].idTipoHorario").value(1))
                .andExpect(jsonPath("$[1].idTipoHorario").value(2))

                .andExpect(jsonPath("$[0].nombre").value("AM"))
                .andExpect(jsonPath("$[1].nombre").value("PM"))
                
                .andExpect(jsonPath("$[0].horaInicio").value("07:00:00"))
                .andExpect(jsonPath("$[1].horaInicio").value("15:00:00"))

                .andExpect(jsonPath("$[0].horaFin").value("15:00:00"))
                .andExpect(jsonPath("$[1].horaFin").value("23:00:00"));            

        verify(tipoHorarioService, times(1)).getAllTipoHorario();

    } 

    @Test
    @DisplayName("GET /tipo-horario/{id} - Debe retornar un tipo de horario")
    void getTipoHorario_ObtenerUnTipoDeHorarioCorrespondienteAlIdDado() throws Exception {
        
        when(tipoHorarioService.getTipoHorario(1L)).thenReturn(am);

        mockMvc.perform(get("/tipo-horario/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.idTipoHorario").value(1))
                .andExpect(jsonPath("$.nombre").value("AM"))
                .andExpect(jsonPath("$.horaInicio").value("07:00:00"))
                .andExpect(jsonPath("$.horaFin").value("15:00:00"));

        verify(tipoHorarioService).getTipoHorario(1L);

    } 
}
