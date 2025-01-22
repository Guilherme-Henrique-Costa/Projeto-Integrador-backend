package org.example.voluntariadointeligentehub.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "candidaturas")
public class Candidatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "voluntario_id", nullable = false)
    private Long voluntarioId;  // ID do voluntário que se candidatou

    @Column(name = "vaga_id", nullable = false)
    private Long vagaId;  // ID da vaga à qual o voluntário se candidatou

    @Column(nullable = false)
    private LocalDateTime dataCandidatura;

    @Column(nullable = false)
    private String status;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVoluntarioId() {
        return voluntarioId;
    }

    public void setVoluntarioId(Long voluntarioId) {
        this.voluntarioId = voluntarioId;
    }

    public Long getVagaId() {
        return vagaId;
    }

    public void setVagaId(Long vagaId) {
        this.vagaId = vagaId;
    }

    public LocalDateTime getDataCandidatura() {
        return dataCandidatura;
    }

    public void setDataCandidatura(LocalDateTime dataCandidatura) {
        this.dataCandidatura = dataCandidatura;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
