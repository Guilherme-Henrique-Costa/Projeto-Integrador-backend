package org.example.voluntariadointeligentehub.service;

import org.example.voluntariadointeligentehub.entity.Candidatura;
import org.example.voluntariadointeligentehub.repository.CandidaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidaturaService {

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    // Salva uma nova candidatura com status "Aceito" imediatamente
    public Candidatura salvarCandidatura(Candidatura candidatura) {
        // Verifica se o voluntário já se candidatou para essa vaga
        List<Candidatura> candidaturasExistentes = candidaturaRepository.findByVagaIdAndVoluntarioId(candidatura.getVagaId(), candidatura.getVoluntarioId());
        if (!candidaturasExistentes.isEmpty()) {
            throw new IllegalArgumentException("Este voluntário já se candidatou para esta vaga.");
        }

        // Definir o status automaticamente como "Aceito"
        candidatura.setStatus("Aceito");

        return candidaturaRepository.save(candidatura);
    }

    // Lista as candidaturas para uma vaga específica
    public List<Candidatura> listarCandidaturasPorVaga(Long vagaId) {
        return candidaturaRepository.findByVagaId(vagaId);
    }

    // Lista as candidaturas de um voluntário específico (opcional)
    public List<Candidatura> listarCandidaturasPorVoluntario(Long voluntarioId) {
        return candidaturaRepository.findByVoluntarioId(voluntarioId);
    }
}
