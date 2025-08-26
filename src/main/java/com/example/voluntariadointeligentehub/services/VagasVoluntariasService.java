package com.example.voluntariadointeligentehub.services;

import com.example.voluntariadointeligentehub.dto.CandidaturaDTO;
import com.example.voluntariadointeligentehub.entities.VagasVoluntarias;
import com.example.voluntariadointeligentehub.repositories.VagasVoluntariasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VagasVoluntariasService {

    private static final Logger log = LoggerFactory.getLogger(VagasVoluntariasService.class);

    @Autowired
    private VagasVoluntariasRepository repository;

    @Transactional
    public ResponseEntity<?> candidatar(VagasVoluntarias candidatura) {
        long start = System.currentTimeMillis();
        System.out.println("[VagasVoluntariasService] candidatar voluntarioId="
                + (candidatura.getVoluntario() != null ? candidatura.getVoluntario().getId() : null)
                + " vagaId=" + (candidatura.getVaga() != null ? candidatura.getVaga().getId() : null));
        Long volId = candidatura.getVoluntario() != null ? candidatura.getVoluntario().getId() : null;
        Long vagaId = candidatura.getVaga() != null ? candidatura.getVaga().getId() : null;
        log.info("Candidatar(volId={}, vagaId={})", volId, vagaId);

        // Evita duplicidade (voluntário já se candidatou à mesma vaga)
        boolean exists = repository.existsByVoluntario_IdAndVaga_Id(volId, vagaId);
        log.debug("existsByVoluntario_IdAndVaga_Id({}, {}) -> {}", volId, vagaId, exists);
        if (exists) {
            log.warn("Candidatura duplicada: volId={} vagaId={}", volId, vagaId);
            return ResponseEntity.status(409).body("Voluntário já está cadastrado nesta vaga.");
        }

        if (candidatura.getDataCandidatura() == null) {
            candidatura.setDataCandidatura(LocalDate.now());
        }

        VagasVoluntarias salva = repository.save(candidatura);
        log.info("Candidatura criada id={} ({}ms)", salva.getId(), (System.currentTimeMillis() - start));
        System.out.println("[VagasVoluntariasService] candidatar OK id=" + salva.getId());
        return ResponseEntity.ok(salva);
    }

    @Transactional(readOnly = true)
    public List<CandidaturaDTO> listarCandidatosPorVaga(Long vagaId) {
        long start = System.currentTimeMillis();
        System.out.println("[VagasVoluntariasService] listarCandidatosPorVaga vagaId=" + vagaId);
        List<CandidaturaDTO> lista = repository.findByVagaIdFetch(vagaId)
                .stream()
                .map(CandidaturaDTO::new)  // construtor a partir de VagasVoluntarias
                .collect(Collectors.toList());
        log.info("listarCandidatosPorVaga(vagaId={}) -> {} itens ({}ms)",
                vagaId, lista.size(), (System.currentTimeMillis() - start));
        return lista;
    }
}
