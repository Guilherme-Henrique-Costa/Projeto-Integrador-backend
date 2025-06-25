package com.example.voluntariadointeligentehub.controllers;

import com.example.voluntariadointeligentehub.entities.VagasVoluntarias;
import com.example.voluntariadointeligentehub.services.VagasVoluntariasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class VagasVoluntariasController {

    @Autowired
    private VagasVoluntariasService service;

    @PostMapping("/candidaturas")
    public ResponseEntity<?> candidatar(@RequestBody VagasVoluntarias candidatura) {
        return service.candidatar(candidatura);
    }

    @GetMapping("/vagasDisponiveis")
    public ResponseEntity<?> listarVagasDisponiveis() {
        return service.getTodasVagasDisponiveis();
    }

    @GetMapping("/vagasCandidatadas/{voluntarioId}")
    public ResponseEntity<?> listarMinhasCandidaturas(@PathVariable Long voluntarioId) {
        return service.getVagasPorVoluntario(voluntarioId);
    }
}