package com.example.voluntariadointeligentehub.repositories;

import com.example.voluntariadointeligentehub.entities.VagasVoluntarias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VagasVoluntariasRepository extends JpaRepository<VagasVoluntarias, Long> {

    List<VagasVoluntarias> findByVoluntarioId(Long voluntarioId);

    boolean existsByVoluntario_IdAndVaga_Id(Long voluntarioId, Long vagaId);

    @Query("""
           select vv
           from VagasVoluntarias vv
           join fetch vv.voluntario v
           join fetch vv.vaga vg
           where vg.id = :vagaId
           """)
    List<VagasVoluntarias> findByVagaIdFetch(@Param("vagaId") Long vagaId);
}
