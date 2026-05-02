package com.albertolizana.ms_citas_medicas.controller;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        List<SlotHorarioResponseDTO> lsh = slotHorarioService.getAllSlots();
        lsh.forEach(this::crearLinkSlotHoirario);
        return ResponseEntity.ok(lsh);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlotHorarioResponseDTO> getSlot(@PathVariable Long id){
        SlotHorarioResponseDTO sl = slotHorarioService.getSlot(id);
        crearLinkSlotHoirario(sl);
        return ResponseEntity.ok(sl);
    }

    @GetMapping("/all/{disponible}")
    public ResponseEntity<List<SlotHorarioResponseDTO>> getAllSlotsByEstado(@PathVariable boolean disponible){
        List<SlotHorarioResponseDTO> lsh = slotHorarioService.getAllSlotsByCita(disponible);
        lsh.forEach(this::crearLinkSlotHoirario);
        return ResponseEntity.ok(lsh);
    }

    private void crearLinkSlotHoirario(SlotHorarioResponseDTO slotHorario){

        slotHorario.add(linkTo(methodOn(SlotHorarioController.class)
                .getSlot(slotHorario.getIdSlotHorario()))
                .withSelfRel());
        
        slotHorario.add(linkTo(methodOn(SlotHorarioController.class)
                .getAllSlots())
                .withRel("collection"));
        
        slotHorario.add(linkTo(methodOn(SlotHorarioController.class)
                .getAllSlotsByEstado(true))
                .withSelfRel());

    }

}
