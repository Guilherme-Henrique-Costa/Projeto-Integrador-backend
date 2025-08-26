package com.example.voluntariadointeligentehub.repositories;

import com.example.voluntariadointeligentehub.entities.Voluntario;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.*;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {

    Optional<Voluntario> findByEmailInstitucional(String emailInstitucional);
    Optional<Voluntario> findByCpf(String cpf);
    Optional<Voluntario> findByNome(String nome);

    // exemplo de filtro por disponibilidade (texto livre)
    List<Voluntario> findByDisponibilidadeSemanalContaining(String turno);

    // >>> NOVO: lista direta por instituição (evita navegar pela entidade Instituicao)
    List<Voluntario> findByInstituicao_Id(Long instituicaoId);

    // >>> NOVO: a mesma consulta acima, mas já ordenando por nome
    List<Voluntario> findByInstituicao_IdOrderByNomeAsc(Long instituicaoId);

    // Busca geral (nome/email/cidade)
    @Query("""
           SELECT v FROM Voluntario v
           WHERE (:q IS NULL OR :q = '' OR
                 LOWER(v.nome)              LIKE LOWER(CONCAT('%', :q, '%')) OR
                 LOWER(v.emailInstitucional)LIKE LOWER(CONCAT('%', :q, '%')) OR
                 LOWER(v.cidadeUF)          LIKE LOWER(CONCAT('%', :q, '%')))
           ORDER BY v.nome ASC
           """)
    List<Voluntario> search(@Param("q") String q);

    // >>> NOVO: busca filtrando por instituição + termo (para a tela de gestão)
    @Query("""
           SELECT v FROM Voluntario v
           WHERE v.instituicao.id = :instituicaoId
             AND (:q IS NULL OR :q = '' OR
                 LOWER(v.nome)              LIKE LOWER(CONCAT('%', :q, '%')) OR
                 LOWER(v.emailInstitucional)LIKE LOWER(CONCAT('%', :q, '%')) OR
                 LOWER(v.cidadeUF)          LIKE LOWER(CONCAT('%', :q, '%')))
           ORDER BY v.nome ASC
           """)
    List<Voluntario> searchByInstituicao(@Param("instituicaoId") Long instituicaoId,
                                         @Param("q") String q);

    // (opcional) caso você tenha coleções que queira carregar de uma vez:
    // @EntityGraph(attributePaths = {"habilidades"})
    // List<Voluntario> findByInstituicao_Id(Long instituicaoId);
}
