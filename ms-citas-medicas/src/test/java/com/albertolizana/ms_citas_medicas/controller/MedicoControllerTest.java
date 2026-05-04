package com.albertolizana.ms_citas_medicas.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;


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

import com.albertolizana.ms_citas_medicas.dto.EspecialidadResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.MedicoResponseDTO;
import com.albertolizana.ms_citas_medicas.service.MedicoService;

@WebMvcTest(MedicoController.class)
@DisplayName("Pruebas de Integración - MedicoController")
public class MedicoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicoService medicoService;

    private List<MedicoResponseDTO> medicos;

    private MedicoResponseDTO medicoUno;
    private MedicoResponseDTO medicoDos;

    private EspecialidadResponseDTO especialidadUno;
    private EspecialidadResponseDTO especialidadDos;

    @BeforeEach
    void setUp(){

        medicos = new ArrayList<>();

        especialidadUno = EspecialidadResponseDTO.builder()
                .idEspecialidad(1L)
                .nombreEspecialidad("Cardiologo")
                .build();

        especialidadDos = EspecialidadResponseDTO.builder()
                .idEspecialidad(2L)
                .nombreEspecialidad("Traumatologo")
                .build();

        medicoUno = MedicoResponseDTO.builder()
                .idMedico(1L)
                .nombre("Juan Antonio")
                .especialidad(especialidadUno)
                .build();

        medicoDos = MedicoResponseDTO.builder()
                .idMedico(2L)
                .nombre("Alberto Lizana")
                .especialidad(especialidadDos)
                .build();
        
        medicos.add(medicoUno);
        medicos.add(medicoDos);
    }


    @Test
    @DisplayName("GET /medico/all - Obtener todos los médicos")
    void getAllMedicos_ObtenerComoResultadoUnaListaDeTodosLosMedicos() throws Exception {

        when(medicoService.getAllMedicos()).thenReturn(medicos);

        mockMvc.perform(get("/medico/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

		.andExpect(jsonPath("$").isArray())
		.andExpect(jsonPath("$", hasSize(2)))
		.andExpect(jsonPath("$[0].idMedico").value(1))
		.andExpect(jsonPath("$[1].idMedico").value(2))

		.andExpect(jsonPath("$[0].nombre").value("Juan Antonio"))
		.andExpect(jsonPath("$[1].nombre").value("Alberto Lizana"))        

		.andExpect(jsonPath("$[0].especialidad.nombreEspecialidad").value(especialidadUno.getNombreEspecialidad()))
		.andExpect(jsonPath("$[1].especialidad.nombreEspecialidad").value(especialidadDos.getNombreEspecialidad()));                       
                
        verify(medicoService, times(1)).getAllMedicos();
    }


    @Test
    @DisplayName("GET /medico/{id} - Obtener un solo médico por id")
    void getMedico_ObtenerComoResultadoUnMedicoEspecificoPorSuId() throws Exception {

        when(medicoService.getMedico(1L)).thenReturn(medicoUno);

        mockMvc.perform(get("/medico/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

		.andExpect(jsonPath("$.idMedico").value(1L))
		.andExpect(jsonPath("$.nombre").value("Juan Antonio"))  
		.andExpect(jsonPath("$.especialidad.nombreEspecialidad").value(especialidadUno.getNombreEspecialidad()));

        verify(medicoService).getMedico(1L);
    }


}
