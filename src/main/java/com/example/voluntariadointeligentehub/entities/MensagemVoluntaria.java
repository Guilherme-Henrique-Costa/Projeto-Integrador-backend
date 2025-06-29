package com.example.voluntariadointeligentehub.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_mensagem_voluntaria")
public class MensagemVoluntaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String voluntarioNome;

    @Column(length = 240, nullable = false)
    private String mensagemVoluntario;

    @Column(nullable = false)
    private Boolean ehUsuario;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "voluntario_id", nullable = false)
    private Voluntario voluntario;

    public MensagemVoluntaria() {}

    public MensagemVoluntaria(Long id, String voluntarioNome, String mensagemVoluntario, Boolean ehUsuario, LocalDateTime dataHora, Voluntario voluntario) {
        this.id = id;
        this.voluntarioNome = voluntarioNome;
        this.mensagemVoluntario = mensagemVoluntario;
        this.ehUsuario = ehUsuario;
        this.dataHora = dataHora;
        this.voluntario = voluntario;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public String getVoluntarioNome() { return voluntarioNome; }
    public String getMensagemVoluntario() { return mensagemVoluntario; }
    public Boolean getEhUsuario() { return ehUsuario; }
    public LocalDateTime getDataHora() { return dataHora; }
    public Voluntario getVoluntario() { return voluntario; }

    public void setVoluntarioNome(String nome) { this.voluntarioNome = nome; }
    public void setMensagemVoluntario(String msg) { this.mensagemVoluntario = msg; }
    public void setEhUsuario(Boolean ehUsuario) { this.ehUsuario = ehUsuario; }
    public void setDataHora(LocalDateTime dataHora) { this.dataHora = dataHora; }
    public void setVoluntario(Voluntario v) { this.voluntario = v; }
}
