package com.example.voluntariadointeligentehub.controllers;

import com.example.voluntariadointeligentehub.dto.CandidaturaDTO;
import com.example.voluntariadointeligentehub.entities.Candidatura;
import com.example.voluntariadointeligentehub.services.CandidaturaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/candidaturas")
@CrossOrigin(origins = "*")
public class CandidaturaController {

    private static final Logger log = LoggerFactory.getLogger(CandidaturaController.class);

    @Autowired
    private CandidaturaService service;

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody Candidatura candidatura) {
        long start = System.currentTimeMillis();
        System.out.println("[CandidaturaController] POST /api/v1/candidaturas volId="
                + (candidatura.getVoluntario() != null ? candidatura.getVoluntario().getId() : null)
                + " vagaId=" + (candidatura.getVaga() != null ? candidatura.getVaga().getId() : null));
        log.info("Cadastrar candidatura volId={} vagaId={}",
                candidatura.getVoluntario() != null ? candidatura.getVoluntario().getId() : null,
                candidatura.getVaga() != null ? candidatura.getVaga().getId() : null);

        ResponseEntity<?> resp = service.cadastrar(candidatura);
        log.info("POST /candidaturas -> status={} ({}ms)", resp.getStatusCode(), (System.currentTimeMillis() - start));
        return resp;
    }

    @GetMapping("/vaga/{vagaId}")
    public ResponseEntity<List<CandidaturaDTO>> listarPorVaga(@PathVariable Long vagaId) {
        long start = System.currentTimeMillis();
        System.out.println("[CandidaturaController] GET /vaga/" + vagaId);
        log.info("Listar candidaturas por vagaId={}", vagaId);
        var resp = service.listarPorVaga(vagaId);
        log.info("GET /vaga/{} -> {} itens ({}ms)", vagaId, resp.getBody().size(), (System.currentTimeMillis() - start));
        return resp;
    }

    @GetMapping("/voluntario/{voluntarioId}")
    public ResponseEntity<List<CandidaturaDTO>> listarPorVoluntario(@PathVariable Long voluntarioId) {
        long start = System.currentTimeMillis();
        System.out.println("[CandidaturaController] GET /voluntario/" + voluntarioId);
        log.info("Listar candidaturas por voluntarioId={}", voluntarioId);
        var resp = service.listarPorVoluntario(voluntarioId);
        log.info("GET /voluntario/{} -> {} itens ({}ms)", voluntarioId, resp.getBody().size(), (System.currentTimeMillis() - start));
        return resp;
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<String> atualizarStatusCandidatura(@PathVariable Long id, @RequestParam String status) {
        boolean atualizado = service.atualizarStatus(id, status);
        if (atualizado) {
            return ResponseEntity.ok("Status da candidatura atualizado com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Erro ao atualizar o status da candidatura.");
        }
    }
}
