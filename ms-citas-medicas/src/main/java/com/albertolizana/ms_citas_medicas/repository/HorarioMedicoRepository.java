package com.albertolizana.ms_citas_medicas.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.albertolizana.ms_citas_medicas.model.HorarioMedico;
import com.albertolizana.ms_citas_medicas.model.Medico;
import com.albertolizana.ms_citas_medicas.model.PlantillaHorario;

@Repository
public interface HorarioMedicoRepository extends JpaRepository<HorarioMedico, Long>{
    List<HorarioMedico> findByFechaGreaterThanEqual(LocalDate fecha);
    boolean existsByMedicoAndFechaAndPlantillaHorario(Medico medico, LocalDate fecha, PlantillaHorario plantillaHorario);
}
