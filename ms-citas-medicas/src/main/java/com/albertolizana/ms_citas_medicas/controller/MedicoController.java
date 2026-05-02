package com.albertolizana.ms_citas_medicas.controller;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        List<MedicoResponseDTO> lm = medicoService.getAllMedicos();
        lm.forEach(this::crearLinksMedico);
        return ResponseEntity.ok(lm);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoResponseDTO> getMedico(@PathVariable Long id){
        MedicoResponseDTO m = medicoService.getMedico(id);
        crearLinksMedico(m);
        return ResponseEntity.ok(m);
    }    


    private void crearLinksMedico(MedicoResponseDTO medico){

        medico.add(linkTo(methodOn(MedicoController.class)
                .getMedico(medico.getIdMedico()))
                .withSelfRel());
        
        medico.add(linkTo(methodOn(MedicoController.class)
                .getAllMedicos())
                .withRel("collection"));
    }
}
