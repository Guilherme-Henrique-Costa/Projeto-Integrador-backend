package com.example.voluntariadointeligentehub.dto;

import com.example.voluntariadointeligentehub.entities.Candidatura;
import com.example.voluntariadointeligentehub.entities.VagasVoluntarias;

import java.time.LocalDate;

public class CandidaturaDTO {

    private Long voluntarioId;
    private Long vagaId;
    private String nomeVoluntario;
    private String emailVoluntario;
    private LocalDate dataCandidatura;
    private String status;

    public CandidaturaDTO() {}

    public CandidaturaDTO(Candidatura c) {
        if (c.getVoluntario() != null) {
            this.voluntarioId = c.getVoluntario().getId();
            this.nomeVoluntario = c.getVoluntario().getNome();
            this.emailVoluntario = c.getVoluntario().getEmailInstitucional();
        }
        if (c.getVaga() != null) {
            this.vagaId = c.getVaga().getId();
        }
        this.dataCandidatura = c.getDataCandidatura();
        this.status = c.getStatus();
    }

    public CandidaturaDTO(VagasVoluntarias vv) {
        if (vv.getVoluntario() != null) {
            this.voluntarioId = vv.getVoluntario().getId();
            this.nomeVoluntario = vv.getVoluntario().getNome();
            this.emailVoluntario = vv.getVoluntario().getEmailInstitucional();
        }
        if (vv.getVaga() != null) {
            this.vagaId = vv.getVaga().getId();
        }
        this.dataCandidatura = vv.getDataCandidatura();
        this.status = null;
    }

    public static CandidaturaDTO fromEntity(Candidatura c) { return new CandidaturaDTO(c); }
    public static CandidaturaDTO fromEntity(VagasVoluntarias vv) { return new CandidaturaDTO(vv); }

    public Long getVoluntarioId() { return voluntarioId; }
    public void setVoluntarioId(Long voluntarioId) { this.voluntarioId = voluntarioId; }

    public Long getVagaId() { return vagaId; }
    public void setVagaId(Long vagaId) { this.vagaId = vagaId; }

    public String getNomeVoluntario() { return nomeVoluntario; }
    public void setNomeVoluntario(String nomeVoluntario) { this.nomeVoluntario = nomeVoluntario; }

    public String getEmailVoluntario() { return emailVoluntario; }
    public void setEmailVoluntario(String emailVoluntario) { this.emailVoluntario = emailVoluntario; }

    public LocalDate getDataCandidatura() { return dataCandidatura; }
    public void setDataCandidatura(LocalDate dataCandidatura) { this.dataCandidatura = dataCandidatura; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
