package org.example.voluntariadointeligentehub.repository;

import org.example.voluntariadointeligentehub.entity.Voluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CadastroVoluntarioRepository extends JpaRepository<Voluntario, Long> {
    // Você pode adicionar métodos personalizados aqui, se necessário
    Optional<Voluntario> findByEmail(String email);
}
