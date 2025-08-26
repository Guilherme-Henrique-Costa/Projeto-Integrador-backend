package com.example.voluntariadointeligentehub.dto;

import com.example.voluntariadointeligentehub.entities.ServicoVoluntario;

import java.time.LocalDateTime;

public class ServicoVoluntarioResponse {
    private Long id;
    private Long voluntarioId;
    private String voluntarioNome;
    private String voluntarioEmail;
    private Long instituicaoId;
    private String instituicaoNome;
    private String descricao;
    private Integer horas;
    private LocalDateTime createdAt;

    public static ServicoVoluntarioResponse fromEntity(ServicoVoluntario s) {
        ServicoVoluntarioResponse dto = new ServicoVoluntarioResponse();
        dto.id = s.getId();
        dto.descricao = s.getDescricao();
        dto.horas = s.getHoras();
        dto.createdAt = s.getCreatedAt();

        if (s.getVoluntario() != null) {
            dto.voluntarioId = s.getVoluntario().getId();
            dto.voluntarioNome = s.getVoluntario().getNome();
            dto.voluntarioEmail = s.getVoluntario().getEmailInstitucional();
        }
        if (s.getInstituicao() != null) {
            dto.instituicaoId = s.getInstituicao().getId();
            dto.instituicaoNome = s.getInstituicao().getNome();
        }
        return dto;
    }

    // getters
    public Long getId() { return id; }
    public Long getVoluntarioId() { return voluntarioId; }
    public String getVoluntarioNome() { return voluntarioNome; }
    public String getVoluntarioEmail() { return voluntarioEmail; }
    public Long getInstituicaoId() { return instituicaoId; }
    public String getInstituicaoNome() { return instituicaoNome; }
    public String getDescricao() { return descricao; }
    public Integer getHoras() { return horas; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}