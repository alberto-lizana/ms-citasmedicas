package com.albertolizana.ms_citas_medicas.controller;

import java.util.List;

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
        return ResponseEntity.ok(tipoHorarioService.getAllTipoHorario());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoHorarioResponseDTO> getTipoHorario(@PathVariable Long id){
        return ResponseEntity.ok(tipoHorarioService.getTipoHorario(id));
    }    
}
