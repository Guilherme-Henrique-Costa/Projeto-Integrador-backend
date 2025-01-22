package org.example.voluntariadointeligentehub.repository;

import org.example.voluntariadointeligentehub.entity.Candidatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {

    // Retorna todas as candidaturas para uma vaga específica
    List<Candidatura> findByVagaId(Long vagaId);

    // Retorna todas as candidaturas de um voluntário para uma vaga específica
    List<Candidatura> findByVagaIdAndVoluntarioId(Long vagaId, Long voluntarioId);

    // Retorna todas as candidaturas de um voluntário
    List<Candidatura> findByVoluntarioId(Long voluntarioId);

    // Retorna os IDs das vagas que possuem candidatos
    @Query("SELECT DISTINCT c.vagaId FROM Candidatura c")
    List<Long> findDistinctVagaIds();
}
