package com.example.voluntariadointeligentehub.controllers;

import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.entities.VagaInstituicao;
import com.example.voluntariadointeligentehub.repositories.InstituicaoRepository;
import com.example.voluntariadointeligentehub.services.VagaInstituicaoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/vagasInstituicao")
@CrossOrigin(origins = "*")
public class VagaInstituicaoController {

    private static final Logger log = LoggerFactory.getLogger(VagaInstituicaoController.class);

    @Autowired
    private VagaInstituicaoService service;

    @Autowired
    private InstituicaoRepository instituicaoRepo;

    @PostMapping
    public ResponseEntity<?> cadastrar(@Valid @RequestBody VagaInstituicao vaga) {
        long start = System.currentTimeMillis();
        System.out.println("[VagaInstituicaoController] POST /api/v1/vagasInstituicao (cadastrar)");
        log.info("Cadastrar vaga -> instituicaoId={}", vaga.getInstituicao() != null ? vaga.getInstituicao().getId() : null);

        if (vaga.getInstituicao() == null || vaga.getInstituicao().getId() == null) {
            log.warn("Cadastro de vaga rejeitado: instituicao.id é obrigatório");
            return ResponseEntity.badRequest().body("instituicao.id é obrigatório");
        }

        Instituicao inst = instituicaoRepo.findById(vaga.getInstituicao().getId()).orElse(null);
        if (inst == null) {
            log.warn("Cadastro de vaga rejeitado: instituição {} não encontrada", vaga.getInstituicao().getId());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Instituição não encontrada");
        }

        vaga.setInstituicao(inst);
        VagaInstituicao criada = service.cadastrar(vaga);

        long took = System.currentTimeMillis() - start;
        log.info("Vaga criada id={} para instituicaoId={} ({}ms)", criada.getId(), inst.getId(), took);
        System.out.println("[VagaInstituicaoController] cadastro OK id=" + criada.getId() + " (" + took + "ms)");

        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

    @GetMapping
    public ResponseEntity<List<VagaInstituicao>> listar(@RequestParam(required = false) Long instituicaoId) {
        long start = System.currentTimeMillis();
        System.out.println("[VagaInstituicaoController] GET /api/v1/vagasInstituicao?instituicaoId=" + instituicaoId);
        log.info("Listar vagas (instituicaoId={})", instituicaoId);

        List<VagaInstituicao> lista = (instituicaoId != null)
                ? service.listarPorInstituicao(instituicaoId)
                : service.listar();

        long took = System.currentTimeMillis() - start;
        log.info("Listar vagas -> {} itens ({}ms)", lista.size(), took);
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagaInstituicao> buscar(@PathVariable Long id) {
        System.out.println("[VagaInstituicaoController] GET /api/v1/vagasInstituicao/" + id);
        log.info("Buscar vaga id={}", id);
        return service.buscarPorId(id)
                .map(v -> {
                    log.info("Vaga encontrada id={}", v.getId());
                    return ResponseEntity.ok(v);
                })
                .orElseGet(() -> {
                    log.warn("Vaga não encontrada id={}", id);
                    return ResponseEntity.notFound().build();
                });
    }

    @GetMapping("/com-candidatos")
    public ResponseEntity<List<VagaInstituicao>> listarComCandidatos(@RequestParam(required = false) Long instituicaoId) {
        long start = System.currentTimeMillis();
        System.out.println("[VagaInstituicaoController] GET /com-candidatos?instituicaoId=" + instituicaoId);
        log.info("Listar vagas com candidatos (instituicaoId={})", instituicaoId);

        List<VagaInstituicao> lista = (instituicaoId != null)
                ? service.listarComCandidatosPorInstituicao(instituicaoId)
                : service.listarComCandidatos();

        long took = System.currentTimeMillis() - start;
        log.info("Listar vagas com candidatos -> {} itens ({}ms)", lista.size(), took);
        return ResponseEntity.ok(lista);
    }
}
