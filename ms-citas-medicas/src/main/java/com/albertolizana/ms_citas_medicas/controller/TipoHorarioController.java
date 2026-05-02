package com.albertolizana.ms_citas_medicas.controller;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albertolizana.ms_citas_medicas.dto.TipoHorarioResponseDTO;
import com.albertolizana.ms_citas_medicas.service.TipoHorarioService;

@RestController
@RequestMapping("/tipo-horario")
public class TipoHorarioController {

    private final TipoHorarioService tipoHorarioService;

    public TipoHorarioController(TipoHorarioService tipoHorarioService){
        this.tipoHorarioService = tipoHorarioService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<TipoHorarioResponseDTO>> getAllTipoHorario(){
        List<TipoHorarioResponseDTO> lth = tipoHorarioService.getAllTipoHorario();
        lth.forEach(this::crearLinksTipoHorario);
        return ResponseEntity.ok(lth);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoHorarioResponseDTO> getTipoHorario(@PathVariable Long id){
        TipoHorarioResponseDTO th = tipoHorarioService.getTipoHorario(id);
        crearLinksTipoHorario(th);
        return ResponseEntity.ok(th);
    }    

    private void crearLinksTipoHorario(TipoHorarioResponseDTO tipoHorario){
        
        tipoHorario.add(linkTo(methodOn(TipoHorarioController.class)
                .getTipoHorario(tipoHorario.getIdTipoHorario())).withSelfRel());

        tipoHorario.add(linkTo(methodOn(TipoHorarioController.class)
                .getAllTipoHorario()).withRel("collection"));
    }
}
