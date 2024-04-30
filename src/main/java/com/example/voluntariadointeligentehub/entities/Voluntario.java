package com.example.voluntariadointeligentehub.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_voluntario")
public class Voluntario  implements Serializable {

    private static final long serialVersionUID = 5935836358441880615L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String email;

    private String password;

    private String interesse;

    private String competencia;

    public Voluntario() {

    }

    public Voluntario(UUID id, String nome, String email, String password, String interesse, String competencia) {

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.password = password;
        this.interesse = interesse;
        this.competencia = competencia;
    }

    //Getters
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

    public String getCompetencia() {
        return competencia;
    }

    //Setters
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

    public void setCompetencia(String competencia){
        this.competencia = competencia;
}
}