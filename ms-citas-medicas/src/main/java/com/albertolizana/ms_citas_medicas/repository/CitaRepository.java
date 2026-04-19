package com.albertolizana.ms_citas_medicas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.albertolizana.ms_citas_medicas.model.Cita;

@Repository
public interface CitaRepository extends JpaRepository<Cita, Long>{

    @Query
    ("""
        SELECT c FROM Cita c
        JOIN FETCH c.estadoCita
        JOIN FETCH c.paciente
        JOIN FETCH c.slotHorario
    """)
    List<Cita> findAllWithRelations();

    @Query
    ("""
        SELECT c FROM Cita c
        JOIN FETCH c.estadoCita
        JOIN FETCH c.paciente
        JOIN FETCH c.slotHorario
        WHERE c.idCita = :id
    """)
    Optional<Cita> findWithRelations(@Param("id") Long id);

    List<Cita> findByEstadoCitaNombre(String nombreEstado);
}