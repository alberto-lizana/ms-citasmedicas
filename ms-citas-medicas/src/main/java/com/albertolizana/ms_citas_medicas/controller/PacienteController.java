package com.albertolizana.ms_citas_medicas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albertolizana.ms_citas_medicas.dto.PacienteResponseDTO;
import com.albertolizana.ms_citas_medicas.service.PacienteService;


@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService){
        this.pacienteService = pacienteService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PacienteResponseDTO>> getAllPacientes(){
        return ResponseEntity.ok(pacienteService.getAllPacientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> getPaciente(@PathVariable Long id){
        return ResponseEntity.ok(pacienteService.getPaciente(id));
    }

    @GetMapping("/all/{estado}")
    public ResponseEntity<List<PacienteResponseDTO>> getAllPacientes(@PathVariable boolean estado){
        return ResponseEntity.ok(pacienteService.getPacientesPorEstado(estado));
    }

    @DeleteMapping("/fisico/{id}")
    public ResponseEntity<?> borrarPacientFisico(@PathVariable Long id){
        pacienteService.borrarPacienteFisico(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/logico/{id}")
    public ResponseEntity<?> borrarPacientlogico(@PathVariable Long id){
        pacienteService.borrarPacienteLogico(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }    
}
