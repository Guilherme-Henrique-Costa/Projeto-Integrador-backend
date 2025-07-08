package com.example.voluntariadointeligentehub.services;

import com.example.voluntariadointeligentehub.dto.CandidaturaDTO;
import com.example.voluntariadointeligentehub.entities.Candidatura;
import com.example.voluntariadointeligentehub.repositories.CandidaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidaturaService {

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    public ResponseEntity<List<CandidaturaDTO>> listarPorVaga(Long vagaId) {
        List<CandidaturaDTO> dtos = candidaturaRepository.findByVagaId(vagaId)
                .stream()
                .map(CandidaturaDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<List<CandidaturaDTO>> listarPorVoluntario(Long voluntarioId) {
        List<CandidaturaDTO> dtos = candidaturaRepository.findByVoluntarioId(voluntarioId)
                .stream()
                .map(CandidaturaDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    public ResponseEntity<?> cadastrar(Candidatura candidatura) {
        boolean jaExiste = candidaturaRepository.existsByVoluntarioIdAndVagaId(
                candidatura.getVoluntario().getId(),
                candidatura.getVaga().getId()
        );

        if (jaExiste) {
            return ResponseEntity.status(409).body("Voluntário já está cadastrado nesta vaga.");
        }

        Candidatura nova = candidaturaRepository.save(candidatura);
        return ResponseEntity.ok(nova);
    }
}
