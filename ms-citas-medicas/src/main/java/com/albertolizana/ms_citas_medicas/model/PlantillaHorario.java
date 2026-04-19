package com.albertolizana.ms_citas_medicas.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="plantilla_horario")
@AllArgsConstructor
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Getter
@Setter
@Builder
public class PlantillaHorario {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Long idPlantillaHorario;

    @Column(name="duracion_minutos", nullable=false)
    private int duracionMinutos;

    @OneToMany(mappedBy="plantillaHorario", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<HorarioMedico> horariosMedico;
    
    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="idTipoHorario")
    private TipoHorario tipoHorario;

}
