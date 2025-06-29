package com.example.voluntariadointeligentehub.services;

import com.example.voluntariadointeligentehub.dto.VagasVoluntariasDTO;
import com.example.voluntariadointeligentehub.entities.VagaInstituicao;
import com.example.voluntariadointeligentehub.entities.VagasVoluntarias;
import com.example.voluntariadointeligentehub.entities.Voluntario;
import com.example.voluntariadointeligentehub.repositories.VagaInstituicaoRepository;
import com.example.voluntariadointeligentehub.repositories.VagasVoluntariasRepository;
import com.example.voluntariadointeligentehub.repositories.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VagasVoluntariasService {

    @Autowired
    private VagasVoluntariasRepository repository;

    @Autowired
    private VagaInstituicaoRepository vagaRepo;

    @Autowired
    private VoluntarioRepository voluntarioRepo;

    @Transactional
    public ResponseEntity<?> candidatar(VagasVoluntarias candidatura) {
        VagaInstituicao vaga = vagaRepo.findById(candidatura.getVaga().getId()).orElse(null);
        Voluntario voluntario = voluntarioRepo.findById(candidatura.getVoluntario().getId()).orElse(null);

        if (vaga == null || voluntario == null) {
            return ResponseEntity.badRequest().body("Voluntário ou vaga inválida.");
        }

        candidatura.setVaga(vaga);
        candidatura.setVoluntario(voluntario);
        candidatura.setDataCandidatura(LocalDate.now());

        repository.save(candidatura);
        return ResponseEntity.ok("Candidatura registrada com sucesso.");
    }

    @Transactional(readOnly = true)
    public ResponseEntity<?> getTodasVagasDisponiveis() {
        List<VagaInstituicao> vagas = vagaRepo.findAll();
        return ResponseEntity.ok(vagas);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<VagasVoluntariasDTO>> getVagasPorVoluntario(Long voluntarioId) {
        List<VagasVoluntarias> candidaturas = repository.findByVoluntarioId(voluntarioId);
        List<VagasVoluntariasDTO> dtos = candidaturas.stream()
                .map(VagasVoluntariasDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}