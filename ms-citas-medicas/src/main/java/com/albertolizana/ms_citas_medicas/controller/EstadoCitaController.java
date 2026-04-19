package com.albertolizana.ms_citas_medicas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albertolizana.ms_citas_medicas.dto.EstadoCitaResponseDTO;
import com.albertolizana.ms_citas_medicas.service.EstadoCitaService;

@RestController
@RequestMapping("/estado-cita")
public class EstadoCitaController {

    private final EstadoCitaService estadoCitaService;

    public EstadoCitaController(EstadoCitaService estadoCitaService){
        this.estadoCitaService = estadoCitaService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EstadoCitaResponseDTO>> getAllEstadoCita(){
        return ResponseEntity.ok(estadoCitaService.getAllEstadoCita());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoCitaResponseDTO> getEstadoCita(@PathVariable Long id){
        return ResponseEntity.ok(estadoCitaService.getEstadoCita(id));
    }
}
