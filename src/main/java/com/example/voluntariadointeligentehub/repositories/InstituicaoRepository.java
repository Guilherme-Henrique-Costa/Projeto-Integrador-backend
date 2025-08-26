package com.example.voluntariadointeligentehub.repositories;

import com.example.voluntariadointeligentehub.entities.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
    Optional<Instituicao> findByNome(String nome);
    Optional<Instituicao> findByEmail(String email);

    // >>> OPCIONAL: carrega voluntários junto (evita LazyInitializationException fora de @Transactional)
    @Query("""
           SELECT i FROM Instituicao i
           LEFT JOIN FETCH i.voluntarios
           WHERE i.id = :id
           """)
    Optional<Instituicao> findByIdFetchVoluntarios(Long id);
}
