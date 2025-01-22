package org.example.voluntariadointeligentehub.repository;

import org.example.voluntariadointeligentehub.entity.PerfilInstituicao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilInstituicaoRepository extends JpaRepository<PerfilInstituicao, Long> {

    // Busca instituição pelo e-mail
    Optional<PerfilInstituicao> findByEmail(String email);

    // Busca instituição pelo CNPJ
    Optional<PerfilInstituicao> findByCnpj(String cnpj);
}
