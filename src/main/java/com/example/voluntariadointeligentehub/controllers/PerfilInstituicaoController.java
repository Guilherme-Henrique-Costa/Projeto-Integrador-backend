package com.example.voluntariadointeligentehub.controllers;

import java.util.List;
import java.util.Optional;

import com.example.voluntariadointeligentehub.entities.PerfilInstituicao;
import com.example.voluntariadointeligentehub.services.PerfilInstituicaoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/perfil-instituicao")
@CrossOrigin(origins = "*")
public class PerfilInstituicaoController {

    private static final Logger log = LoggerFactory.getLogger(PerfilInstituicaoController.class);

    @Autowired
    private PerfilInstituicaoService service;

    // ===== Fluxo principal sem DTO =====

    // 1) Buscar perfil por email da instituição (pode não existir ainda)
    @GetMapping("/email")
    public ResponseEntity<Optional<PerfilInstituicao>> findByEmail(@RequestParam String email) {
        long start = System.currentTimeMillis();
        System.out.println("[PerfilInstituicaoController] GET /email?email=" + email);
        log.info("Buscando PerfilInstituicao por email='{}'", email);
        var resp = service.findByEmail(email);
        log.debug("findByEmail('{}) -> present={}", email, resp.getBody().isPresent());
        System.out.println("[PerfilInstituicaoController] /email OK (" + (System.currentTimeMillis() - start) + "ms)");
        return resp;
    }

    // 2) Buscar perfil por instituicaoId
    @GetMapping("/instituicao/{instituicaoId}")
    public ResponseEntity<Optional<PerfilInstituicao>> findByInstituicaoId(@PathVariable Long instituicaoId) {
        long start = System.currentTimeMillis();
        System.out.println("[PerfilInstituicaoController] GET /instituicao/" + instituicaoId);
        log.info("Buscando PerfilInstituicao por instituicaoId={}", instituicaoId);
        var resp = service.findByInstituicaoId(instituicaoId);
        log.debug("findByInstituicaoId({}) -> present={}", instituicaoId, resp.getBody().isPresent());
        System.out.println("[PerfilInstituicaoController] /instituicao/{id} OK (" + (System.currentTimeMillis() - start) + "ms)");
        return resp;
    }

    // 3) Upsert por instituicaoId (cria se não existir, atualiza se existir)
    @PostMapping("/instituicao/{instituicaoId}")
    public ResponseEntity<PerfilInstituicao> upsert(@PathVariable Long instituicaoId,
                                                    @Valid @RequestBody PerfilInstituicao body) {
        long start = System.currentTimeMillis();
        System.out.println("[PerfilInstituicaoController] POST /instituicao/" + instituicaoId + " (upsert)");
        log.info("Upsert PerfilInstituicao instituicaoId={}", instituicaoId);
        var resp = service.upsertByInstituicaoId(instituicaoId, body);
        log.info("Upsert concluído instituicaoId={} perfilId={}", instituicaoId, resp.getBody().getId());
        System.out.println("[PerfilInstituicaoController] upsert OK (" + (System.currentTimeMillis() - start) + "ms)");
        return resp;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PerfilInstituicao>> findAll() {
        System.out.println("[PerfilInstituicaoController] GET /all");
        var resp = service.findAll();
        log.info("Listar todos PerfisInstituicao -> {} itens",
                resp.getBody() != null ? resp.getBody().size() : 0);
        return resp;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PerfilInstituicao>> findById(@PathVariable Long id) {
        System.out.println("[PerfilInstituicaoController] GET /{id} id=" + id);
        log.info("Buscando PerfilInstituicao id={}", id);
        return service.findById(id);
    }

    @PostMapping
    public ResponseEntity<PerfilInstituicao> create(@Valid @RequestBody PerfilInstituicao perfil) {
        System.out.println("[PerfilInstituicaoController] POST / (create) instituicaoId="
                + (perfil.getInstituicao() != null ? perfil.getInstituicao().getId() : null));
        log.info("Criando PerfilInstituicao para instituicaoId={}",
                perfil.getInstituicao() != null ? perfil.getInstituicao().getId() : null);
        return new ResponseEntity<>(service.create(perfil), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfilInstituicao> update(@PathVariable Long id, @Valid @RequestBody PerfilInstituicao body) {
        System.out.println("[PerfilInstituicaoController] PUT /{id} id=" + id);
        log.info("Atualizando PerfilInstituicao id={}", id);
        var resp = ResponseEntity.of(service.update(id, body));
        log.info("Atualização PerfilInstituicao id={} -> status={}", id, resp.getStatusCode());
        return resp;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        System.out.println("[PerfilInstituicaoController] DELETE /{id} id=" + id);
        log.info("Excluindo PerfilInstituicao id={}", id);
        boolean ok = service.delete(id);
        log.info("Exclusão PerfilInstituicao id={} -> {}", id, ok ? "DELETADO" : "NÃO ENCONTRADO");
        return ok ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
