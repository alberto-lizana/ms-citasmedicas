package com.albertolizana.ms_citas_medicas.model;

import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="slot_horario")
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class SlotHorario {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long idSlotHorario;

    @Column(name="hora_inicio", nullable=false)
    private LocalTime horaInicio;
    
    @Column(name="hora_fin", nullable=false)
    private LocalTime horaFin;

    @OneToOne(mappedBy="slotHorario")
    private Cita cita;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="idHorarioMedico", nullable=false)
    private HorarioMedico horarioMedico;

}
