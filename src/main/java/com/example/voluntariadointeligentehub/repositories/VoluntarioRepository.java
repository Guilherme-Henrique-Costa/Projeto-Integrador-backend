package com.example.voluntariadointeligentehub.repositories;

import com.example.voluntariadointeligentehub.entities.VagasVoluntarias;
import com.example.voluntariadointeligentehub.entities.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
    Optional<Voluntario> findByPassword(String password);
    Optional<Voluntario> findByEmail(String email);
    Optional<Voluntario> findByNome(String nome);
}
