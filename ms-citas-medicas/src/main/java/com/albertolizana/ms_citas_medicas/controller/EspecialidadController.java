package com.albertolizana.ms_citas_medicas.controller;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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
        List<EspecialidadResponseDTO> le = especialidadService.getAllEspecialidades();
        le.forEach(this::crearLinksEspecialidad);
        return ResponseEntity.ok(le);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecialidadResponseDTO> getEspecialidad(@PathVariable Long id){
        EspecialidadResponseDTO e = especialidadService.getEspecialidad(id);
        crearLinksEspecialidad(e);
        return ResponseEntity.ok(e);
    }

    private void crearLinksEspecialidad(EspecialidadResponseDTO especialidad){

        especialidad.add(linkTo(methodOn(EspecialidadController.class)
                .getEspecialidad(especialidad.getIdEspecialidad())).withSelfRel());
        
        especialidad.add(linkTo(methodOn(EspecialidadController.class)
                .getAllEspecialidades()).withRel("collection"));

    }
}
