package com.example.voluntariadointeligentehub.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(
        name = "tb_candidatura", // <-- nome exclusivo para não conflitar com VagasVoluntarias
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"voluntario_id", "vaga_id"})
        }
)
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "vaga_id")
    private VagaInstituicao vaga;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "voluntario_id")
    private Voluntario voluntario;

    private LocalDate dataCandidatura;

    @Column(length = 40)
    private String status; // PENDENTE, APROVADO, RECUSADO etc.

    @PrePersist
    public void prePersist() {
        if (dataCandidatura == null) dataCandidatura = LocalDate.now();
        if (status == null || status.isBlank()) status = "PENDENTE";
        // prints úteis para acompanhar inserts
        System.out.println("[CandidaturaEntity] prePersist volId=" +
                (voluntario != null ? voluntario.getId() : null) +
                " vagaId=" + (vaga != null ? vaga.getId() : null) +
                " data=" + dataCandidatura +
                " status=" + status);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public VagaInstituicao getVaga() { return vaga; }
    public void setVaga(VagaInstituicao vaga) { this.vaga = vaga; }

    public Voluntario getVoluntario() { return voluntario; }
    public void setVoluntario(Voluntario voluntario) { this.voluntario = voluntario; }

    public LocalDate getDataCandidatura() { return dataCandidatura; }
    public void setDataCandidatura(LocalDate dataCandidatura) { this.dataCandidatura = dataCandidatura; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
