package com.albertolizana.ms_citas_medicas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albertolizana.ms_citas_medicas.dto.CrearHorariosRequestDTO;
import com.albertolizana.ms_citas_medicas.dto.HorarioMedicoResponseDTO;
import com.albertolizana.ms_citas_medicas.service.HorarioMedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/horario-medico")
public class HorarioMedicoController {

    private final HorarioMedicoService horarioMedicoService;

    public HorarioMedicoController(HorarioMedicoService horarioMedicoService){
        this.horarioMedicoService = horarioMedicoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<HorarioMedicoResponseDTO>> getAllHorariosMedico(){
        return ResponseEntity.ok(horarioMedicoService.getAllHorariosMedico());
    };

    @GetMapping("/{id}")
    public ResponseEntity<HorarioMedicoResponseDTO> getHorarioMedico(@PathVariable Long id){
        return ResponseEntity.ok(horarioMedicoService.getHorarioMedico(id));
    };

    @PostMapping("/crear-horarios")
    public ResponseEntity<?> crearHorarios(@Valid @RequestBody CrearHorariosRequestDTO dto){
        horarioMedicoService.crearHorarios(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
