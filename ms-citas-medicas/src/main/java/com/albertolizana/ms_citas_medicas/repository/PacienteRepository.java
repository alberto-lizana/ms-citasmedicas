package com.albertolizana.ms_citas_medicas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertolizana.ms_citas_medicas.model.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    List<Paciente> findByEstado(Boolean estado);
}
