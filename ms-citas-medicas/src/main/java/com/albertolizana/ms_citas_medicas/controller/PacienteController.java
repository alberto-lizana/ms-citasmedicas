package com.albertolizana.ms_citas_medicas.controller;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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
        List<PacienteResponseDTO> lp = pacienteService.getAllPacientes();
        lp.forEach(t -> crearLinkPaciente(t));
        return ResponseEntity.ok(lp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponseDTO> getPaciente(@PathVariable Long id){
        PacienteResponseDTO p = pacienteService.getPaciente(id);
        crearLinkPaciente(p);
        return ResponseEntity.ok(p);
    }

    @GetMapping("/all/{estado}")
    public ResponseEntity<List<PacienteResponseDTO>> getPacientesPorEstado(@PathVariable boolean estado){
        List<PacienteResponseDTO> lp = pacienteService.getPacientesPorEstado(estado);
        lp.forEach(t -> crearLinkPaciente(t));        
        return ResponseEntity.ok(lp);
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


    private void crearLinkPaciente(PacienteResponseDTO paciente){

        paciente.add(linkTo(methodOn(PacienteController.class)
                .getPaciente(paciente.getIdPaciente()))
                .withSelfRel());
        
        paciente.add(linkTo(methodOn(PacienteController.class)
                .getAllPacientes())
                .withRel("collection"));

        paciente.add(linkTo(methodOn(PacienteController.class)
                .getPacientesPorEstado(paciente.getEstado()))
                .withRel("paciente-estado-collection"));

    }
}
