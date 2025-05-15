package com.example.voluntariadointeligentehub.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_instituicao")
public class Instituicao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cnpj;

    @Column(length = 60)
    private String email;

    @Column(length = 20)
    private String password;
    
    @Column(length = 240)
    private String description;

    private int vgaAbrtInstru;

    @Column(length = 5)
    private String avaliacao;

    private String nome;

    private String mensagemInstituicao;

    private String areaInstituicao;

    private int rltIntituicao;

    private int vltInstituicao;

    @Column(length = 150)
    private String competence;

    private String nomeVltInstituicao;

    private String descricaoInstituicao;

    private String interestArea;

    @OneToOne(mappedBy = "instituicao", cascade = CascadeType.ALL)
    private PerfilInstituicao perfilInstituicao;

    @ManyToOne
    @JoinColumn(name = "voluntario_id", nullable = false)
    private Voluntario voluntario;

    public Instituicao() {

    }

    public Instituicao(
        Long id,
        String cnpj,
        String email,
        String password,
        String description,
        int vgaAbrtInstru,
        String avaliacao,
        String nome,
        String mensagemInstituicao,
        String areaInstituicao,
        int rltIntituicao,
        int vltInstituicao,
        String competence,
        String nomeVltInstituicao,
        String descricaoInstituicao,
        Voluntario voluntario
        ) {

        this.id = id;
        this.cnpj = cnpj;
        this.email = email;
        this.password = password;
        this.description = description;
        this.vgaAbrtInstru = vgaAbrtInstru;
        this.avaliacao = avaliacao;
        this.nome = nome;
        this.mensagemInstituicao = mensagemInstituicao;
        this.areaInstituicao = areaInstituicao;
        this.rltIntituicao = rltIntituicao;
        this.vltInstituicao = vltInstituicao;
        this.competence = competence;
        this.nomeVltInstituicao = nomeVltInstituicao;
        this.descricaoInstituicao = descricaoInstituicao;
        this.voluntario = voluntario;
    }

    //Getters
    public Long getId() {
        return id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getDescription() {
        return description;
    }

    public int getVgaAbrtInstru() {
        return vgaAbrtInstru;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public String getNome() {
        return nome;
    }

    public String getMensagemInstituicao() {
        return mensagemInstituicao;
    }

    public String getAreaInstituicao() {
        return areaInstituicao;
    }

    public int getRltIntituicao() {
        return rltIntituicao;
    }

    public int getVltInstituicao() {
        return vltInstituicao;
    }

    public String getCompetence() {
        return competence;
    }

    public String getNomeVltInstituicao() {
        return nomeVltInstituicao;
    }

    public String getDescricaoInstituicao() {
        return descricaoInstituicao;
    }

    public PerfilInstituicao getPerfilInstituicao() {
        return perfilInstituicao;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }


    //Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVgaAbrtInstru(int vgaAbrtInstru){
        this.vgaAbrtInstru = vgaAbrtInstru;
    }

    public void setAvaliacao(String avaliacao){
        this.avaliacao = avaliacao;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setMensagemInstituicao(String mensagemInstituicao){
        this.mensagemInstituicao = mensagemInstituicao;
    }

    public void setAreaInstituicao(String areaInstituicao){
        this.areaInstituicao = areaInstituicao;
    }

    public void setRltIntituicao(int rltIntituicao){
        this.rltIntituicao = rltIntituicao;
    }

    public void setVltInstituicao(int vltInstituicao) {
        this.vltInstituicao = vltInstituicao;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public void setDescricaoInstituicao(String descricaoInstituicao){
        this.descricaoInstituicao = descricaoInstituicao;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }
}
