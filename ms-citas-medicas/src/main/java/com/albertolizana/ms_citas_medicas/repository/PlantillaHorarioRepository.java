package com.albertolizana.ms_citas_medicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertolizana.ms_citas_medicas.model.PlantillaHorario;

@Repository
public interface PlantillaHorarioRepository extends JpaRepository<PlantillaHorario, Long>{

}
