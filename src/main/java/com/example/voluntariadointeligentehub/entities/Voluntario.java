// Voluntario.java
package com.example.voluntariadointeligentehub.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_voluntario")
public class Voluntario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String matricula;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String genero;
    private String senha;

    @ElementCollection
    private List<String> atividadeCEUB;

    private String emailInstitucional;
    private String emailParticular;
    private String celular;
    private String cidadeUF;
    private String horario;
    private String motivacao;

    @ElementCollection
    private List<String> causas;

    @ElementCollection
    private List<String> habilidades;

    @ElementCollection
    private List<String> disponibilidadeSemanal;

    private String comentarios;
    private String avatarPath;

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    @OneToMany(mappedBy = "voluntario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("voluntario-vaga")
    private List<VagasVoluntarias> vagas;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public List<String> getAtividadeCEUB() { return atividadeCEUB; }
    public void setAtividadeCEUB(List<String> atividadeCEUB) { this.atividadeCEUB = atividadeCEUB; }

    public String getEmailInstitucional() { return emailInstitucional; }
    public void setEmailInstitucional(String emailInstitucional) { this.emailInstitucional = emailInstitucional; }

    public String getEmailParticular() { return emailParticular; }
    public void setEmailParticular(String emailParticular) { this.emailParticular = emailParticular; }

    public String getCelular() { return celular; }
    public void setCelular(String celular) { this.celular = celular; }

    public String getCidadeUF() { return cidadeUF; }
    public void setCidadeUF(String cidadeUF) { this.cidadeUF = cidadeUF; }

    public String getHorario() { return horario; }
    public void setHorario(String horario) { this.horario = horario; }

    public String getMotivacao() { return motivacao; }
    public void setMotivacao(String motivacao) { this.motivacao = motivacao; }

    public List<String> getCausas() { return causas; }
    public void setCausas(List<String> causas) { this.causas = causas; }

    public List<String> getHabilidades() { return habilidades; }
    public void setHabilidades(List<String> habilidades) { this.habilidades = habilidades; }

    public List<String> getDisponibilidadeSemanal() { return disponibilidadeSemanal; }
    public void setDisponibilidadeSemanal(List<String> disponibilidadeSemanal) { this.disponibilidadeSemanal = disponibilidadeSemanal; }

    public String getComentarios() { return comentarios; }
    public void setComentarios(String comentarios) { this.comentarios = comentarios; }

    public String getAvatarPath() { return avatarPath; }
    public void setAvatarPath(String avatarPath) { this.avatarPath = avatarPath; }

    public List<VagasVoluntarias> getVagas() { return vagas; }
    public void setVagas(List<VagasVoluntarias> vagas) { this.vagas = vagas; }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

}
