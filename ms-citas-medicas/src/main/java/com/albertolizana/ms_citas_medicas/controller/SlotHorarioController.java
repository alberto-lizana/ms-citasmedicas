package com.albertolizana.ms_citas_medicas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albertolizana.ms_citas_medicas.dto.SlotHorarioResponseDTO;
import com.albertolizana.ms_citas_medicas.service.SlotHorarioService;


@RestController
@RequestMapping("/slot-horario")
public class SlotHorarioController {

    private final SlotHorarioService slotHorarioService;

    public SlotHorarioController(SlotHorarioService slotHorarioService){
        this.slotHorarioService = slotHorarioService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<SlotHorarioResponseDTO>> getAllSlots(){
        return ResponseEntity.ok(slotHorarioService.getAllSlots());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlotHorarioResponseDTO> getSlot(@PathVariable Long id){
        return ResponseEntity.ok(slotHorarioService.getSlot(id));
    }

    @GetMapping("/all/{disponible}")
    public ResponseEntity<List<SlotHorarioResponseDTO>> getAllSlotsByEstado(@PathVariable boolean disponible){
        return ResponseEntity.ok(slotHorarioService.getAllSlotsByCita(disponible));
    }
}
