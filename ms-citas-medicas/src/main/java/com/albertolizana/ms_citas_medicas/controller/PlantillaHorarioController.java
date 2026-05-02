package com.albertolizana.ms_citas_medicas.controller;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        List<PlantillaHorarioResponseDTO> lph = plantillaHorarioService.getAllPlantillaHorario();
        lph.forEach(this::crearLinkPlantillaHorario);
        return ResponseEntity.ok(lph);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantillaHorarioResponseDTO> getPlantillaHorario(@PathVariable Long id){
        PlantillaHorarioResponseDTO ph = plantillaHorarioService.getPlantillaHorario(id);
        crearLinkPlantillaHorario(ph);
        return ResponseEntity.ok(ph);
    }   

    private void crearLinkPlantillaHorario(PlantillaHorarioResponseDTO plantillaHorario){

        plantillaHorario.add(linkTo(methodOn(PlantillaHorarioController.class)
                .getPlantillaHorario(plantillaHorario.getIdPlantilla()))
                .withSelfRel());
        
        plantillaHorario.add(linkTo(methodOn(PlantillaHorarioController.class)
                .getAllPlantillaHorario())
                .withRel("collection"));
    }
}
