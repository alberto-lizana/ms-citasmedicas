package com.albertolizana.ms_citas_medicas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albertolizana.ms_citas_medicas.dto.MedicoResponseDTO;
import com.albertolizana.ms_citas_medicas.service.MedicoService;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    private final MedicoService medicoService;

    public MedicoController(MedicoService medicoService){
        this.medicoService = medicoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicoResponseDTO>> getAllMedicos(){
        return ResponseEntity.ok(medicoService.getAllMedicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> getMedico(@PathVariable Long id){
        return ResponseEntity.ok(medicoService.getMedico(id));
    }    
}
