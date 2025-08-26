package com.example.voluntariadointeligentehub.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegistrarServicoRequest {

    @NotNull
    private Long instituicaoId;

    @NotNull
    private Long voluntarioId;

    @NotBlank
    @Size(max = 4000)
    private String descricao;

    private Integer horas; // opcional

    public Long getInstituicaoId() { return instituicaoId; }
    public Long getVoluntarioId() { return voluntarioId; }
    public String getDescricao() { return descricao; }
    public Integer getHoras() { return horas; }
    public void setInstituicaoId(Long instituicaoId) { this.instituicaoId = instituicaoId; }
    public void setVoluntarioId(Long voluntarioId) { this.voluntarioId = voluntarioId; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setHoras(Integer horas) { this.horas = horas; }
}
