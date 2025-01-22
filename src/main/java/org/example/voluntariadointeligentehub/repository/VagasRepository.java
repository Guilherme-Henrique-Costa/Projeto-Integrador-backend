package org.example.voluntariadointeligentehub.repository;

import org.example.voluntariadointeligentehub.entity.Vagas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagasRepository extends JpaRepository<Vagas, Long> {
}
