package com.example.voluntariadointeligentehub.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.voluntariadointeligentehub.entities.PerfilInstituicao;

public interface PerfilInstituicaoRepository extends JpaRepository<PerfilInstituicao, Long> {
    Optional<PerfilInstituicao> findByInstituicaoId(Long instituicaoId);
    Optional<PerfilInstituicao> findByInstituicaoEmail(String email);
}
