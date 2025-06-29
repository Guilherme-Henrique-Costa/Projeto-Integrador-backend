package com.example.voluntariadointeligentehub.repositories;

import com.example.voluntariadointeligentehub.entities.VagasVoluntarias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VagasVoluntariasRepository extends JpaRepository<VagasVoluntarias, Long> {
    List<VagasVoluntarias> findByVoluntarioId(Long voluntarioId);
}
