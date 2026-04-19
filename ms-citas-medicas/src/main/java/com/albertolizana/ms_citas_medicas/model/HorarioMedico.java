package com.albertolizana.ms_citas_medicas.model;

import java.time.LocalDate;
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
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name="horario_medico",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_medico_fecha_plantilla",
            columnNames = {"idMedico", "fecha", "idPlantillaHorario"}
        )
    }
)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class HorarioMedico {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long idHorarioMedico;

    @Column(name="fecha", nullable=false)
    private LocalDate fecha;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="idMedico", nullable=false)
    private Medico medico;

    @ManyToOne(fetch=FetchType.LAZY, optional=false)
    @JoinColumn(name="idPlantillaHorario", nullable=false)
    private PlantillaHorario plantillaHorario;

    @OneToMany(mappedBy="horarioMedico", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<SlotHorario> slots;

}

