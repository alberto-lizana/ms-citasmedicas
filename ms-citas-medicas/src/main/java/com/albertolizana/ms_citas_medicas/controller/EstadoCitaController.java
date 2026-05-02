package com.albertolizana.ms_citas_medicas.controller;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        List<EstadoCitaResponseDTO> lec = estadoCitaService.getAllEstadoCita();
        lec.forEach(this::crearLinksEstadoCita);
        return ResponseEntity.ok(lec);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoCitaResponseDTO> getEstadoCita(@PathVariable Long id){
        EstadoCitaResponseDTO ec = estadoCitaService.getEstadoCita(id);
        crearLinksEstadoCita(ec);
        return ResponseEntity.ok(ec);
    }

    private void crearLinksEstadoCita(EstadoCitaResponseDTO estadoCita){

        estadoCita.add(linkTo(methodOn(EstadoCitaController.class)
                .getEstadoCita(estadoCita.getIdEstadoCita())).withSelfRel());
        
        estadoCita.add(linkTo(methodOn(EstadoCitaController.class)
                .getAllEstadoCita()).withRel("collection"));

    }
}
