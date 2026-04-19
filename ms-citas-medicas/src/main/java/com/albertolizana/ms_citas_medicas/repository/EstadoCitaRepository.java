package com.albertolizana.ms_citas_medicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertolizana.ms_citas_medicas.model.EstadoCita;

@Repository
public interface EstadoCitaRepository extends JpaRepository<EstadoCita, Long>{
    public EstadoCita findByNombre(String nombre);
}
