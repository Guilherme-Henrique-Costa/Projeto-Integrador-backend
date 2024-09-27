package com.example.voluntariadointeligentehub.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_vagas_voluntarias")
public class VagasVoluntarias {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricaoVaga;

    private String area;

    private String vagaAbrt;

    private String vlt;

    @ManyToOne
    @JoinColumn(name = "voluntario_id", nullable = false)
    private Voluntario voluntario;
    
    public VagasVoluntarias() {

    }

    public VagasVoluntarias(
        Long id,
        String descricaoVaga,
        String area,
        String vagaAbrt,
        String vlt,
        Voluntario voluntario
        ) {

        this.id = id;
        this.descricaoVaga = descricaoVaga;
        this.area = area;
        this.vagaAbrt = vagaAbrt;
        this.vlt = vlt;
        this.voluntario = voluntario;
    }

    //Getters
    public String getDescricaoVaga() {
        return descricaoVaga;
    }

    public String getArea() {
        return area;
    }

    public String getVagaAbrt() {
        return vagaAbrt;
    }

    public String getVlt() {
        return vlt;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    //Setters
    public void setDescricaoVaga(String descricaoVaga) {
        this.descricaoVaga = descricaoVaga;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public void setVagaAbrt(String vagaAbrt) {
        this.vagaAbrt = vagaAbrt;
    }

    public void setVlt(String vlt) {
        this.vlt = vlt;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }
}
