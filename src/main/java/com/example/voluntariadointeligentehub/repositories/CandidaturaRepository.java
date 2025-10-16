package com.example.voluntariadointeligentehub.repositories;

import com.example.voluntariadointeligentehub.entities.Candidatura;
import com.example.voluntariadointeligentehub.entities.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidaturaRepository extends JpaRepository<Candidatura, Long> {

    // 🔹 Já existentes
    List<Candidatura> findByVagaId(Long vagaId);
    List<Candidatura> findByVoluntarioId(Long voluntarioId);
    boolean existsByVoluntarioIdAndVagaId(Long voluntarioId, Long vagaId);

    // 🔹 Novos métodos usados no Dashboard (estatísticas)
    @Query("SELECT COUNT(c) FROM Candidatura c WHERE c.voluntario.id = :id")
    long countByVoluntarioId(@Param("id") Long id);

    @Query("SELECT COUNT(c) FROM Candidatura c WHERE c.voluntario.id = :id AND c.status = :status")
    long countByVoluntarioIdAndStatus(@Param("id") Long id, @Param("status") String status);

    @Query("SELECT DISTINCT c.vaga.instituicao FROM Candidatura c WHERE c.voluntario.id = :id")
    List<Instituicao> findDistinctInstituicoesByVoluntarioId(@Param("id") Long id);
}
