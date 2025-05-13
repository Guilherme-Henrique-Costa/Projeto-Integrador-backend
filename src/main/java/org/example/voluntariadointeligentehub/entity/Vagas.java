package org.example.voluntariadointeligentehub.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
@Table(name = "vagas")
public class Vagas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cargo;
    private String localidade;
    private String descricao;

    @ElementCollection
    private List<String> especificacoes;

    private String tipoVaga;  // Adicionando o campo tipoVaga

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<String> getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(List<String> especificacoes) {
        this.especificacoes = especificacoes;
    }

    public String getTipoVaga() {
        return tipoVaga;
    }

    public void setTipoVaga(String tipoVaga) {
        this.tipoVaga = tipoVaga;
    }
}
