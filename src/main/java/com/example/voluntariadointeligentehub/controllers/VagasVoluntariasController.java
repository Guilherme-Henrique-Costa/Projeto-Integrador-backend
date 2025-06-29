package com.example.voluntariadointeligentehub.controllers;

import com.example.voluntariadointeligentehub.dto.VagasVoluntariasDTO;
import com.example.voluntariadointeligentehub.entities.VagasVoluntarias;
import com.example.voluntariadointeligentehub.repositories.VagasVoluntariasRepository;
import com.example.voluntariadointeligentehub.services.VagasVoluntariasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class VagasVoluntariasController {

    @Autowired
    private VagasVoluntariasService service;

    @Autowired
    private VagasVoluntariasRepository candidaturaRepository;

    @PostMapping("/candidaturas")
    public ResponseEntity<?> candidatar(@RequestBody VagasVoluntarias candidatura) {
        return service.candidatar(candidatura);
    }

    @GetMapping("/vagasDisponiveis")
    public ResponseEntity<?> listarVagasDisponiveis() {
        return service.getTodasVagasDisponiveis();
    }

    @GetMapping("/vagasCandidatadas/{voluntarioId}")
    public ResponseEntity<List<VagasVoluntariasDTO>> buscarCandidatadas(@PathVariable Long voluntarioId) {
        List<VagasVoluntarias> candidaturas = candidaturaRepository.findByVoluntarioId(voluntarioId);
        List<VagasVoluntariasDTO> dtos = candidaturas.stream()
                .map(VagasVoluntariasDTO::fromEntity)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dtos);
    }
}
