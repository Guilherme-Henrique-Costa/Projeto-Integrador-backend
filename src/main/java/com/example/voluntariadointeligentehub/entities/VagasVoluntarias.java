package com.example.voluntariadointeligentehub.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_vagas_voluntarias")
public class VagasVoluntarias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vaga_id", nullable = false)
    @JsonBackReference("vaga-voluntaria")
    private VagaInstituicao vaga;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voluntario_id", nullable = false)
    @JsonBackReference("voluntario-vaga")
    private Voluntario voluntario;

    private LocalDate dataCandidatura;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public VagaInstituicao getVaga() { return vaga; }
    public void setVaga(VagaInstituicao vaga) { this.vaga = vaga; }

    public Voluntario getVoluntario() { return voluntario; }
    public void setVoluntario(Voluntario voluntario) { this.voluntario = voluntario; }

    public LocalDate getDataCandidatura() { return dataCandidatura; }
    public void setDataCandidatura(LocalDate dataCandidatura) { this.dataCandidatura = dataCandidatura; }
}
