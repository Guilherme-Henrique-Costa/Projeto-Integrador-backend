package com.example.voluntariadointeligentehub.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_mensagem_voluntaria")
public class MensagemVoluntaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String voluntarioNome;

    @Column(length = 240)
    private String mensagemVoluntario;

    @ManyToOne
    @JoinColumn(name = "voluntario_id", nullable = false)
    private Voluntario voluntario;
    
    public MensagemVoluntaria() {

    }

    public MensagemVoluntaria(
        Long id,
        String voluntarioNome,
        String mensagemVoluntario,
        Voluntario voluntario
        ) {

        this.id = id;
        this.voluntarioNome = voluntarioNome;
        this.mensagemVoluntario = mensagemVoluntario;
        this.voluntario = voluntario;
    }

    //Getters
    public String getVoluntarioNome() {
        return voluntarioNome;
    }

    public String getMensagemVoluntario() {
        return mensagemVoluntario;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    //Setters
    public void setVoluntarioNome(String voluntarioNome) {
        this.voluntarioNome = voluntarioNome;
    }

    public void setMensagemVoluntario(String mensagemVoluntario) {
        this.mensagemVoluntario = mensagemVoluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }
}
