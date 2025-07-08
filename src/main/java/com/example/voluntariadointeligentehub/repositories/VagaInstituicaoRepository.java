package com.example.voluntariadointeligentehub.repositories;

import com.example.voluntariadointeligentehub.entities.VagaInstituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VagaInstituicaoRepository extends JpaRepository<VagaInstituicao, Long> {
    @Query("SELECT v FROM VagaInstituicao v LEFT JOIN FETCH v.candidaturas")
    List<VagaInstituicao> findAllWithCandidaturas();

}
