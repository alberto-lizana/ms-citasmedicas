package com.albertolizana.ms_citas_medicas.controller;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albertolizana.ms_citas_medicas.dto.CitaResponseDTO;
import com.albertolizana.ms_citas_medicas.dto.CrearReservaCitaRequestDTO;
import com.albertolizana.ms_citas_medicas.dto.CrearReservaCitaResponseDTO;
import com.albertolizana.ms_citas_medicas.service.CitaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cita")
public class CitaController {

    private final CitaService citaService;

    public CitaController(CitaService citaService){
        this.citaService = citaService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<CitaResponseDTO>> getAllCitas(){
        List<CitaResponseDTO> lc = citaService.getAllCitas();
        lc.forEach(this::crearLinksCita);
        return ResponseEntity.ok(lc);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaResponseDTO> getCita(@PathVariable Long id){
        CitaResponseDTO c = citaService.getCita(id);
        crearLinksCita(c);
        return ResponseEntity.ok(c);
    }

    @GetMapping("/estado/{nombreEstado}")
    public ResponseEntity<List<CitaResponseDTO>> getCitasPorEstado(@PathVariable String nombreEstado){
        return ResponseEntity.ok(citaService.getCitasPorEstado(nombreEstado));
    }

    @PostMapping("/reservar") 
    public ResponseEntity<CrearReservaCitaResponseDTO> crearReservaCita(@Valid @RequestBody CrearReservaCitaRequestDTO dto){
        CrearReservaCitaResponseDTO r = citaService.crearReservaCita(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(r);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> borrarCita(@PathVariable Long id){
            citaService.borrarCita(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private void crearLinksCita(CitaResponseDTO cita){
        
        cita.add(linkTo(methodOn(CitaController.class)
            .getCita(cita.getIdCita()))
            .withSelfRel());
        
        cita.add(linkTo(methodOn(CitaController.class)
            .getAllCitas()).withRel("collection"));
        
        cita.add(linkTo(methodOn(CitaController.class).getCitasPorEstado(cita.getEstadoCita().getNombre())).withRel("cita-estado-collection"));

        cita.add(linkTo(methodOn(CitaController.class)
            .crearReservaCita(null))
            .withRel("create"));
        
        cita.add(linkTo(methodOn(CitaController.class)
            .borrarCita(cita.getIdCita())).withRel("delete"));

    }
}
