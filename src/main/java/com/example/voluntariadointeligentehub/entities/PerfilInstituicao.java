package com.example.voluntariadointeligentehub.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_perfil_institucional")
public class PerfilInstituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cnpj;

    private String email;

    private String password;
    
    private String nome;

    @OneToOne
    @JoinColumn(name = "instituicao_id", nullable = false)
    private Instituicao instituicao;
    
    public PerfilInstituicao() {

    }

    public PerfilInstituicao(
        Long id,
        int cnpj,
        String email,
        String password,
        String nome,
        Instituicao instituicao
        ) {

        this.id = id;
        this.cnpj = cnpj;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.instituicao = instituicao;
    }

    //Getters
    public int getCnpj() {
        return cnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNome() {
        return nome;
    }

    //Setters
    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
