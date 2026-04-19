package com.albertolizana.ms_citas_medicas.model;

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
@Table(name="pacientes")
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Paciente {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private Long idPaciente;

    @Column(name="nombre", nullable=false, length=100)
    private String nombre;

    @Column(name="email", nullable=false, length=150, unique=true)
    private String email;

    @Column(name="estado", nullable=false)
    private Boolean estado;

    @OneToMany(mappedBy="paciente", cascade=CascadeType.ALL, orphanRemoval=true)
    private List<Cita> citas;

}
