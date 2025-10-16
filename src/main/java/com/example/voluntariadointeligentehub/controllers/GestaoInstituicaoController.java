package com.example.voluntariadointeligentehub.controllers;

import com.example.voluntariadointeligentehub.dto.RegistrarServicoRequest;
import com.example.voluntariadointeligentehub.dto.ServicoVoluntarioResponse;
import com.example.voluntariadointeligentehub.services.GestaoInstituicaoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gestao")
@CrossOrigin(origins = "*")
public class GestaoInstituicaoController {

    private static final Logger log = LoggerFactory.getLogger(GestaoInstituicaoController.class);

    private final GestaoInstituicaoService service;

    public GestaoInstituicaoController(GestaoInstituicaoService service) {
        this.service = service;
    }

    @PostMapping("/servicos")
    public ResponseEntity<ServicoVoluntarioResponse> registrar(@Valid @RequestBody RegistrarServicoRequest body) {
        log.info("[GestaoController] POST /servicos inst={} vol={}", body.getInstituicaoId(), body.getVoluntarioId());
        return service.registrarServico(body);
    }

    @GetMapping("/servicos/instituicao/{instituicaoId}")
    public ResponseEntity<List<ServicoVoluntarioResponse>> listarPorInstituicao(@PathVariable Long instituicaoId) {
        log.info("[GestaoController] GET /servicos/instituicao/{}", instituicaoId);
        return service.listarPorInstituicao(instituicaoId);
    }

    @GetMapping("/servicos/voluntario/{voluntarioId}")
    public ResponseEntity<List<ServicoVoluntarioResponse>> listarPorVoluntario(@PathVariable Long voluntarioId) {
        log.info("[GestaoController] GET /servicos/voluntario/{}", voluntarioId);
        return service.listarPorVoluntario(voluntarioId);
    }
}
