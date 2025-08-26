package com.example.voluntariadointeligentehub.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_servico_voluntario")
public class ServicoVoluntario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // quem prestou
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "voluntario_id")
    private Voluntario voluntario;

    // para qual instituição
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    @Column(nullable = false, length = 4000)
    private String descricao;

    // opcional: registrar horas
    private Integer horas; // pode ser null

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    // getters/setters
    public Long getId() { return id; }
    public Voluntario getVoluntario() { return voluntario; }
    public Instituicao getInstituicao() { return instituicao; }
    public String getDescricao() { return descricao; }
    public Integer getHoras() { return horas; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setId(Long id) { this.id = id; }
    public void setVoluntario(Voluntario voluntario) { this.voluntario = voluntario; }
    public void setInstituicao(Instituicao instituicao) { this.instituicao = instituicao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setHoras(Integer horas) { this.horas = horas; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}