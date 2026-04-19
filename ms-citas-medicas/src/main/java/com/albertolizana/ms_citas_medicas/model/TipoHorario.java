package com.albertolizana.ms_citas_medicas.model;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="tipo_horario")
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TipoHorario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long idTipoHorario;

    @Column(name="nombre", nullable=false, unique=true, length=100)
    private String nombre;

    @Column(name="hora_inicio", nullable=false)
    private LocalTime horaInicio;

    @Column(name="hora_fin", nullable=false)
    private LocalTime horaFin;

    @OneToMany(mappedBy="tipoHorario", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<PlantillaHorario> plantillas;

}
