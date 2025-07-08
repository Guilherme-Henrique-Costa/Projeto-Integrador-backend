package com.example.voluntariadointeligentehub.repositories;

import java.util.Optional;

import com.example.voluntariadointeligentehub.entities.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.voluntariadointeligentehub.entities.PerfilInstituicao;

public interface PerfilInstituicaoRepository extends JpaRepository<PerfilInstituicao, Long> {
    Optional<Instituicao> findByEmail(String email);
}
