package com.albertolizana.ms_citas_medicas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albertolizana.ms_citas_medicas.dto.PlantillaHorarioResponseDTO;
import com.albertolizana.ms_citas_medicas.service.PlantillaHorarioService;

@RestController
@RequestMapping("/plantilla-horario")
public class PlantillaHorarioController {

    private final PlantillaHorarioService plantillaHorarioService;

    public PlantillaHorarioController(PlantillaHorarioService plantillaHorarioService){
        this.plantillaHorarioService = plantillaHorarioService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PlantillaHorarioResponseDTO>> getAllPlantillaHorario(){
        return ResponseEntity.ok(plantillaHorarioService.getAllPlantillaHorario());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantillaHorarioResponseDTO> getPlantillaHorario(@PathVariable Long id){
        return ResponseEntity.ok(plantillaHorarioService.getPlantillaHorario(id));
    }    
}
