package com.example.voluntariadointeligentehub.repositories;

import com.example.voluntariadointeligentehub.entities.ServicoVoluntario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServicoVoluntarioRepository extends JpaRepository<ServicoVoluntario, Long> {
    List<ServicoVoluntario> findByInstituicao_IdOrderByCreatedAtDesc(Long instituicaoId);
    List<ServicoVoluntario> findByVoluntario_IdOrderByCreatedAtDesc(Long voluntarioId);
}