package com.example.voluntariadointeligentehub.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.voluntariadointeligentehub.entities.VagasVoluntarias;

public interface  VagasVoluntariasRepository extends JpaRepository<VagasVoluntarias, Long> {
    Optional<VagasVoluntarias> findByDescricaoVaga(String descricaoVaga);
    Optional<VagasVoluntarias> findByArea(String area);
    Optional<VagasVoluntarias> findByVagaAbrt(String vagaAbrt);
}
