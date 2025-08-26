package com.example.voluntariadointeligentehub.dto;

import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.entities.VagaInstituicao;
import com.example.voluntariadointeligentehub.entities.VagasVoluntarias;

import java.time.LocalDate;
import java.util.List;

public class VagasVoluntariasDTO {

    private Long id;
    private LocalDate dataCandidatura;
    private String status;

    private Long vagaId;
    private String cargo;
    private String localidade;
    private String descricao;
    private List<String> especificacoes;
    private String tipoVaga;
    private String area;
    private String horario;
    private String tempoVoluntariado;
    private String disponibilidade;

    private Double latitude;
    private Double longitude;
    private String cidade;

    private Long instituicaoId;
    private String instituicaoNome;

    public static VagasVoluntariasDTO fromEntity(VagasVoluntarias entity) {
        VagaInstituicao vaga = entity.getVaga();
        Instituicao inst = vaga.getInstituicao();

        VagasVoluntariasDTO dto = new VagasVoluntariasDTO();
        dto.setId(entity.getId());
        dto.setDataCandidatura(entity.getDataCandidatura());
        dto.setStatus(entity.getStatus());

        dto.setVagaId(vaga.getId());
        dto.setCargo(vaga.getCargo());
        dto.setLocalidade(vaga.getLocalidade());
        dto.setDescricao(vaga.getDescricao());
        dto.setEspecificacoes(vaga.getEspecificacoes());
        dto.setTipoVaga(vaga.getTipoVaga());
        dto.setArea(vaga.getArea());
        dto.setHorario(vaga.getHorario());
        dto.setTempoVoluntariado(vaga.getTempoVoluntariado());
        dto.setDisponibilidade(vaga.getDisponibilidade());

        dto.setLatitude(vaga.getLatitude());
        dto.setLongitude(vaga.getLongitude());
        dto.setCidade(vaga.getCidade());

        dto.setInstituicaoId(inst.getId());
        dto.setInstituicaoNome(inst.getNome());
        return dto;
    }

    // getters e setters ...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDataCandidatura() { return dataCandidatura; }
    public void setDataCandidatura(LocalDate dataCandidatura) { this.dataCandidatura = dataCandidatura; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Long getVagaId() { return vagaId; }
    public void setVagaId(Long vagaId) { this.vagaId = vagaId; }

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

    public Long getInstituicaoId() { return instituicaoId; }
    public void setInstituicaoId(Long instituicaoId) { this.instituicaoId = instituicaoId; }

    public String getInstituicaoNome() { return instituicaoNome; }
    public void setInstituicaoNome(String instituicaoNome) { this.instituicaoNome = instituicaoNome; }
}
