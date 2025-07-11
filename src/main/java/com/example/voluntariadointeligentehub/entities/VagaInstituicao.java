package com.example.voluntariadointeligentehub.entities;
import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.entities.VagasVoluntarias;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    // Novos campos de localização
    private Double latitude;
    private Double longitude;
    private String cidade;

    @ManyToOne
    @JoinColumn(name = "instituicao_id", nullable = false)
    private Instituicao instituicao;

    @OneToMany(mappedBy = "vaga", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("vaga-voluntaria")
    private List<VagasVoluntarias> candidaturas;

    // Getters e Setters
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

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public Instituicao getInstituicao() { return instituicao; }
    public void setInstituicao(Instituicao instituicao) { this.instituicao = instituicao; }

    public List<VagasVoluntarias> getCandidaturas() { return candidaturas; }
    public void setCandidaturas(List<VagasVoluntarias> candidaturas) { this.candidaturas = candidaturas; }
}
