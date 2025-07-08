package com.example.voluntariadointeligentehub.repositories;

import com.example.voluntariadointeligentehub.entities.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {
    List<Candidatura> findByVagaId(Long vagaId);
    List<Candidatura> findByVoluntarioId(Long voluntarioId);
    boolean existsByVoluntarioIdAndVagaId(Long voluntarioId, Long vagaId);
}
