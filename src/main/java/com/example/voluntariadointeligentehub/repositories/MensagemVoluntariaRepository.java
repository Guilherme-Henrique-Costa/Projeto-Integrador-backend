package com.example.voluntariadointeligentehub.repositories;

import com.example.voluntariadointeligentehub.entities.MensagemVoluntaria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MensagemVoluntariaRepository extends JpaRepository<MensagemVoluntaria, Long> {
    List<MensagemVoluntaria> findByVoluntario_IdOrderByDataHoraAsc(Long id);
}
