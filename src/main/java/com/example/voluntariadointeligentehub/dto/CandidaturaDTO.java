package com.example.voluntariadointeligentehub.dto;

import com.example.voluntariadointeligentehub.entities.Candidatura;
import java.time.LocalDate;

public class CandidaturaDTO {
    private String nomeVoluntario;
    private String email;
    private LocalDate dataCandidatura;

    public CandidaturaDTO(Candidatura c) {
        this.nomeVoluntario = c.getVoluntario().getNome();
        this.email = c.getVoluntario().getEmailInstitucional();
        this.dataCandidatura = c.getDataCandidatura();
    }

    public String getNomeVoluntario() {
        return nomeVoluntario;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getDataCandidatura() {
        return dataCandidatura;
    }
}