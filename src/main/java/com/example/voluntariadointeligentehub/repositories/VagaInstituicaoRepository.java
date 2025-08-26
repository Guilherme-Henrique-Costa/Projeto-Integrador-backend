package com.example.voluntariadointeligentehub.repositories;

import com.example.voluntariadointeligentehub.entities.VagaInstituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VagaInstituicaoRepository extends JpaRepository<VagaInstituicao, Long> {

    List<VagaInstituicao> findByInstituicaoId(Long instituicaoId);

    @Query("select v from VagaInstituicao v left join fetch v.candidaturas")
    List<VagaInstituicao> findAllWithCandidaturas();

    @Query("select v from VagaInstituicao v left join fetch v.candidaturas where v.instituicao.id = :instituicaoId")
    List<VagaInstituicao> findByInstituicaoIdWithCandidaturas(@Param("instituicaoId") Long instituicaoId);
}
