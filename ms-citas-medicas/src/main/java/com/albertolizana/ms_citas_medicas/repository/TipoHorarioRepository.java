package com.albertolizana.ms_citas_medicas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertolizana.ms_citas_medicas.model.TipoHorario;

@Repository
public interface TipoHorarioRepository extends JpaRepository<TipoHorario, Long>{

}
