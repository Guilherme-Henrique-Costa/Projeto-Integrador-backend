package com.example.voluntariadointeligentehub.repositories;

import com.example.voluntariadointeligentehub.entities.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface VoluntarioRepository extends JpaRepository<Voluntario, Long> {
    Optional<Voluntario> findByEmailInstitucional(String emailInstitucional);
    Optional<Voluntario> findByCpf(String cpf);
    Optional<Voluntario> findByNome(String nome);
    List<Voluntario> findByDisponibilidadeSemanalContaining(String turno);
}

