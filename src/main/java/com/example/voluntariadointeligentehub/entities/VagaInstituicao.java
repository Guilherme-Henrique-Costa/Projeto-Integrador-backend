package com.example.voluntariadointeligentehub.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tb_vaga_instituicao")
public class VagaInstituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cargo;
    private String localidade;
    private String descricao;

    @ElementCollection
    private List<String> especificacoes;

    private String tipoVaga;
    private String area;
    private String horario;
    private String tempoVoluntariado;
    private String disponibilidade;

    @ManyToOne
    @JoinColumn(name = "instituicao_id", nullable = false)
    private Instituicao instituicao;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCargo() { return cargo; }
    public void setCargo(String cargo) { this.cargo = cargo; }

    public String getLocalidade() { return localidade; }
    public void setLocalidade(String localidade) { this.localidade = localidade; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public List<String> getEspecificacoes() { return especificacoes; }
    public void setEspecificacoes(List<String> especificacoes) { this.especificacoes = especificacoes; }

    public String getTipoVaga() { return tipoVaga; }
    public void setTipoVaga(String tipoVaga) { this.tipoVaga = tipoVaga; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getTempoVoluntariado() { return tempoVoluntariado; }
    public void setTempoVoluntariado(String tempoVoluntariado) { this.tempoVoluntariado = tempoVoluntariado; }

    public String getDisponibilidade() { return disponibilidade; }
    public void setDisponibilidade(String disponibilidade) { this.disponibilidade = disponibilidade; }

    public Instituicao getInstituicao() { return instituicao; }
    public void setInstituicao(Instituicao instituicao) { this.instituicao = instituicao; }
}
