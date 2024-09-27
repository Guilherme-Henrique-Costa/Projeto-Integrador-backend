package com.example.voluntariadointeligentehub.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_feedback_voluntario")
public class FeedbackVoluntario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricaoVaga;

    private String feedback;

    @ManyToOne
    @JoinColumn(name = "voluntario_id", nullable = false)
    private Voluntario voluntario;
    
    public FeedbackVoluntario() {

    }

    public FeedbackVoluntario(
        Long id,
        String descricaoVaga,
        String feedback,
        Voluntario voluntario
        ) {

        this.id = id;
        this.descricaoVaga = descricaoVaga;
        this.feedback = feedback;
        this.voluntario = voluntario;
    }

    //Getters
    public String getDescricaoVaga() {
        return descricaoVaga;
    }

    public String getFeedback() {
        return feedback;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    //Setters
    public void setDescricaoVaga(String descricaoVaga) {
        this.descricaoVaga = descricaoVaga;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }
}
