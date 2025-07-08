package com.example.voluntariadointeligentehub.controllers;

import com.example.voluntariadointeligentehub.dto.CandidaturaDTO;
import com.example.voluntariadointeligentehub.entities.Candidatura;
import com.example.voluntariadointeligentehub.services.CandidaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidaturas")
public class CandidaturaController {

    @Autowired
    private CandidaturaService candidaturaService;

    @GetMapping("/vaga/{vagaId}")
    public ResponseEntity<List<CandidaturaDTO>> listarPorVaga(@PathVariable Long vagaId) {
        return candidaturaService.listarPorVaga(vagaId);
    }

    @GetMapping("/voluntario/{voluntarioId}")
    public ResponseEntity<List<CandidaturaDTO>> listarPorVoluntario(@PathVariable Long voluntarioId) {
        return candidaturaService.listarPorVoluntario(voluntarioId);
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> cadastrar(@RequestBody Candidatura candidatura) {
        return candidaturaService.cadastrar(candidatura);
    }
}
