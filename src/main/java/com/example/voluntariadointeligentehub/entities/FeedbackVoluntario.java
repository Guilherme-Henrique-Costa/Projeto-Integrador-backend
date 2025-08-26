package com.example.voluntariadointeligentehub.entities;

import jakarta.persistence.*;

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

    public FeedbackVoluntario() {}

    public FeedbackVoluntario(Long id, String descricaoVaga, String feedback, Voluntario voluntario) {
        this.id = id;
        this.descricaoVaga = descricaoVaga;
        this.feedback = feedback;
        this.voluntario = voluntario;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescricaoVaga() { return descricaoVaga; }
    public void setDescricaoVaga(String descricaoVaga) { this.descricaoVaga = descricaoVaga; }

    public String getFeedback() { return feedback; }
    public void setFeedback(String feedback) { this.feedback = feedback; }

    public Voluntario getVoluntario() { return voluntario; }
    public void setVoluntario(Voluntario voluntario) { this.voluntario = voluntario; }
}
