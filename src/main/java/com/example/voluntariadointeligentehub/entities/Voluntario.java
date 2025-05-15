package com.example.voluntariadointeligentehub.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_voluntario")
public class Voluntario implements Serializable {

    private static final long serialVersionUID = 5935836358441880615L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 80)
    private String nome;

    @Column(length = 60)
    private String email;

    @Column(length = 20)
    private String password;

    private String interesse;

    private String competence;

    @Column(length = 240)
    private String fdbk;

    @Column(name = "nascimento")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate nascimento;

    private String descricaoVaga;

    @JsonIgnore
    @OneToMany(mappedBy = "voluntario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MensagemVoluntaria> mensagens;

    @JsonIgnore
    @OneToMany(mappedBy = "voluntario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VagasVoluntarias> vagasVoluntarias;

    @JsonIgnore
    @OneToMany(mappedBy = "voluntario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FeedbackVoluntario> feedbackVoluntarias;

    @JsonIgnore
    @OneToMany(mappedBy = "voluntario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Instituicao> instituicao;

    public Voluntario() {

    }

    public Voluntario(
            String nome,
            String email,
            String password,
            String interesse,
            String competence,
            LocalDate nascimento,
            String descricaoVaga,
            String fdbk
    ) {
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.interesse = interesse;
        this.competence = competence;
        this.nascimento = nascimento;
        this.descricaoVaga = descricaoVaga;
        this.fdbk = fdbk;
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getInteresse() {
        return interesse;
    }

    public String getCompetence() {
        return competence;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public String getDescricaoVaga() {
        return descricaoVaga;
    }

    public String getFdbk() {
        return fdbk;
    }

    public List<MensagemVoluntaria> getMensagens() {
        return mensagens;
    }

    public List<VagasVoluntarias> getVagasVoluntarias() {
        return vagasVoluntarias;
    }

    public List<FeedbackVoluntario> getFeedbackVoluntarias() {
        return feedbackVoluntarias;
    }

    public List<Instituicao> getInstituicao() {
        return instituicao;
    }

    //Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setInteresse(String interesse) {
        this.interesse = interesse;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }

    public void setDescricaoVaga(String descricaoVaga) {
        this.descricaoVaga = descricaoVaga;
    }

    public void setFdbk(String fdbk) {
        this.fdbk = fdbk;
    }
}
