package com.albertolizana.ms_citas_medicas.controller;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albertolizana.ms_citas_medicas.dto.EspecialidadResponseDTO;
import com.albertolizana.ms_citas_medicas.service.EspecialidadService;

@RestController
@RequestMapping("/especialidad")
public class EspecialidadController {

    private final EspecialidadService especialidadService;

    public EspecialidadController(EspecialidadService especialidadService){
        this.especialidadService = especialidadService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EspecialidadResponseDTO>> getAllEspecialidades(){
        return ResponseEntity.ok(especialidadService.getAllEspecialidades());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadResponseDTO> getEspecialidad(@PathVariable Long id){
        return ResponseEntity.ok(especialidadService.getEspecialidad(id));
    }    
}
