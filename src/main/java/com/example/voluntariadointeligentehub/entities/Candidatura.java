package com.example.voluntariadointeligentehub.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "tb_vagas_voluntarias",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"voluntario_id", "vaga_id"})
        }
)
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "voluntario_id", nullable = false)
    private Voluntario voluntario;

    @ManyToOne
    @JoinColumn(name = "vaga_id", nullable = false)
    private VagaInstituicao vaga;

    private LocalDate dataCandidatura;

    private String status;

    public Candidatura() {}

    public Candidatura(Voluntario voluntario, VagaInstituicao vaga, LocalDate dataCandidatura, String status) {
        this.voluntario = voluntario;
        this.vaga = vaga;
        this.dataCandidatura = dataCandidatura;
        this.status = status;
    }

    public Long getId() { return id; }
    public Voluntario getVoluntario() { return voluntario; }
    public void setVoluntario(Voluntario voluntario) { this.voluntario = voluntario; }

    public VagaInstituicao getVaga() { return vaga; }
    public void setVaga(VagaInstituicao vaga) { this.vaga = vaga; }

    public LocalDate getDataCandidatura() { return dataCandidatura; }
    public void setDataCandidatura(LocalDate dataCandidatura) { this.dataCandidatura = dataCandidatura; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
