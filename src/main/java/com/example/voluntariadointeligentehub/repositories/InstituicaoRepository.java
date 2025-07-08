package com.example.voluntariadointeligentehub.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.voluntariadointeligentehub.entities.Instituicao;


public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
    Optional<Instituicao> findByNome(String nome);
    Optional<Instituicao> findByEmail(String email);
}
