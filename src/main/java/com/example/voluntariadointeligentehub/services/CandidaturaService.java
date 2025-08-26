package com.example.voluntariadointeligentehub.services;

import com.example.voluntariadointeligentehub.dto.CandidaturaDTO;
import com.example.voluntariadointeligentehub.entities.Candidatura;
import com.example.voluntariadointeligentehub.repositories.CandidaturaRepository;
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
public class CandidaturaService {

    private static final Logger log = LoggerFactory.getLogger(CandidaturaService.class);

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    @Transactional(readOnly = true)
    public ResponseEntity<List<CandidaturaDTO>> listarPorVaga(Long vagaId) {
        System.out.println("[CandidaturaService] listarPorVaga vagaId=" + vagaId);
        List<CandidaturaDTO> dtos = candidaturaRepository.findByVagaId(vagaId)
                .stream()
                .map(CandidaturaDTO::fromEntity)
                .collect(Collectors.toList());
        log.info("listarPorVaga({}) -> {} itens", vagaId, dtos.size());
        return ResponseEntity.ok(dtos);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<CandidaturaDTO>> listarPorVoluntario(Long voluntarioId) {
        System.out.println("[CandidaturaService] listarPorVoluntario voluntarioId=" + voluntarioId);
        List<CandidaturaDTO> dtos = candidaturaRepository.findByVoluntarioId(voluntarioId)
                .stream()
                .map(CandidaturaDTO::fromEntity)
                .collect(Collectors.toList());
        log.info("listarPorVoluntario({}) -> {} itens", voluntarioId, dtos.size());
        return ResponseEntity.ok(dtos);
    }

    @Transactional
    public ResponseEntity<?> cadastrar(Candidatura candidatura) {
        Long volId = candidatura.getVoluntario() != null ? candidatura.getVoluntario().getId() : null;
        Long vagaId = candidatura.getVaga() != null ? candidatura.getVaga().getId() : null;
        System.out.println("[CandidaturaService] cadastrar volId=" + volId + " vagaId=" + vagaId);
        log.info("Cadastrar candidatura volId={} vagaId={}", volId, vagaId);

        boolean jaExiste = candidaturaRepository.existsByVoluntarioIdAndVagaId(volId, vagaId);
        log.debug("existsByVoluntarioIdAndVagaId({}, {}) -> {}", volId, vagaId, jaExiste);
        if (jaExiste) {
            log.warn("Candidatura duplicada para volId={} vagaId={}", volId, vagaId);
            return ResponseEntity.status(409).body("Voluntário já está cadastrado nesta vaga.");
        }

        if (candidatura.getDataCandidatura() == null) {
            candidatura.setDataCandidatura(LocalDate.now());
        }
        if (candidatura.getStatus() == null || candidatura.getStatus().isBlank()) {
            candidatura.setStatus("PENDENTE");
        }

        Candidatura nova = candidaturaRepository.save(candidatura);
        log.info("Candidatura criada id={} volId={} vagaId={}",
                nova.getId(), volId, vagaId);
        System.out.println("[CandidaturaService] cadastrar OK id=" + nova.getId());
        return ResponseEntity.ok(new CandidaturaDTO(nova));
    }
}
