package org.example.voluntariadointeligentehub.controller;

import org.example.voluntariadointeligentehub.entity.Candidatura;
import org.example.voluntariadointeligentehub.service.CandidaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidaturas")
public class CandidaturaController {

    @Autowired
    private CandidaturaService candidaturaService;

    @PostMapping
    public ResponseEntity<?> criarCandidatura(@RequestBody Candidatura candidatura) {
        try {
            Candidatura novaCandidatura = candidaturaService.salvarCandidatura(candidatura);
            return new ResponseEntity<>(novaCandidatura, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro na candidatura: " + e.getMessage());  // Log detalhado
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            System.out.println("Erro interno: " + e.getMessage());  // Log detalhado
            return new ResponseEntity<>("Erro interno no servidor.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para listar candidaturas de uma vaga específica (instituições visualizam candidatos)
    @GetMapping("/vaga/{vagaId}")
    public List<Candidatura> listarCandidatosPorVaga(@PathVariable Long vagaId) {
        return candidaturaService.listarCandidaturasPorVaga(vagaId);
    }

    // Endpoint para listar as candidaturas de um voluntário específico (opcional)
    @GetMapping("/voluntario/{voluntarioId}")
    public List<Candidatura> listarCandidaturasPorVoluntario(@PathVariable Long voluntarioId) {
        return candidaturaService.listarCandidaturasPorVoluntario(voluntarioId);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
