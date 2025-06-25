package com.example.voluntariadointeligentehub.repositories;

import com.example.voluntariadointeligentehub.entities.VagasVoluntarias;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VagasVoluntariasRepository extends JpaRepository<VagasVoluntarias, Long> {
    List<VagasVoluntarias> findByVoluntarioId(Long voluntarioId);
}
