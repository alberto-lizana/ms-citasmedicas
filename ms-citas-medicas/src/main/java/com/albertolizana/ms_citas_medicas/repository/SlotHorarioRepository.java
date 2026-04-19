package com.albertolizana.ms_citas_medicas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertolizana.ms_citas_medicas.model.HorarioMedico;
import com.albertolizana.ms_citas_medicas.model.SlotHorario;

@Repository
public interface SlotHorarioRepository extends JpaRepository<SlotHorario, Long>{
    boolean existsByHorarioMedico(HorarioMedico horarioMedico);
    
    // (disponibles)
    List<SlotHorario> findByCitaIsNull();

    // (ocupados)
    List<SlotHorario> findByCitaIsNotNull();
}
