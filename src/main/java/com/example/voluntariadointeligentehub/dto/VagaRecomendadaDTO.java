package com.example.voluntariadointeligentehub.dto;

public class VagaRecomendadaDTO {

    private Long id;
    private String titulo;
    private String causa;
    private String localidade;

    public VagaRecomendadaDTO() {
    }

    public VagaRecomendadaDTO(Long id, String titulo, String causa, String localidade) {
        this.id = id;
        this.titulo = titulo;
        this.causa = causa;
        this.localidade = localidade;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getCausa() { return causa; }
    public void setCausa(String causa) { this.causa = causa; }

    public String getLocalidade() { return localidade; }
    public void setLocalidade(String localidade) { this.localidade = localidade; }
}
