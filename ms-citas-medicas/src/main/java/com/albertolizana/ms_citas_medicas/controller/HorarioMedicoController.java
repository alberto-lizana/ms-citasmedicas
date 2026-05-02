package com.albertolizana.ms_citas_medicas.controller;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.albertolizana.ms_citas_medicas.dto.CrearHorariosRequestDTO;
import com.albertolizana.ms_citas_medicas.dto.HorarioMedicoResponseDTO;
import com.albertolizana.ms_citas_medicas.service.HorarioMedicoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/horario-medico")
public class HorarioMedicoController {

    private final HorarioMedicoService horarioMedicoService;

    public HorarioMedicoController(HorarioMedicoService horarioMedicoService){
        this.horarioMedicoService = horarioMedicoService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<HorarioMedicoResponseDTO>> getAllHorariosMedico(){
        List<HorarioMedicoResponseDTO> lhm = horarioMedicoService.getAllHorariosMedico();
        lhm.forEach(this::crearLinksHorarioMedico);
        return ResponseEntity.ok(lhm);
    };

    @GetMapping("/{id}")
    public ResponseEntity<HorarioMedicoResponseDTO> getHorarioMedico(@PathVariable Long id){
        HorarioMedicoResponseDTO hm = horarioMedicoService.getHorarioMedico(id);
        crearLinksHorarioMedico(hm);
        return ResponseEntity.ok(hm);
    };

    @PostMapping("/crear-horarios")
    public ResponseEntity<?> crearHorarios(@Valid @RequestBody CrearHorariosRequestDTO dto){
        horarioMedicoService.crearHorarios(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    private void crearLinksHorarioMedico(HorarioMedicoResponseDTO horarioMedico){

        horarioMedico.add(linkTo(methodOn(HorarioMedicoController.class)
                .getHorarioMedico(horarioMedico.getIdHorarioMedico()))
                .withSelfRel());
        
        horarioMedico.add(linkTo(methodOn(HorarioMedicoController.class)
                .getAllHorariosMedico())
                .withRel("collection"));
        
        horarioMedico.add(linkTo(methodOn(HorarioMedicoController.class)
                .crearHorarios(null)).withRel("create-horarios"));
    }
}
