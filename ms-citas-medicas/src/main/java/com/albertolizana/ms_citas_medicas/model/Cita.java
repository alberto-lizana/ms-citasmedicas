package com.albertolizana.ms_citas_medicas.model;

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
@Table(name="citas")
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Cita {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long idCita;

    @OneToOne
    @JoinColumn(name="idSlotHorario", nullable=false, unique=true)
    private SlotHorario slotHorario;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="idEstadoCita", nullable=false)
    private EstadoCita estadoCita; 

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="idPaciente", nullable=false)
    private Paciente paciente; 
 
}
