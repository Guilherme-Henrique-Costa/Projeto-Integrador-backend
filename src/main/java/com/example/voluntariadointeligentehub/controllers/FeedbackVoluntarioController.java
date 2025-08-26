package com.example.voluntariadointeligentehub.controllers;

import com.example.voluntariadointeligentehub.entities.FeedbackVoluntario;
import com.example.voluntariadointeligentehub.services.FeedbackVoluntarioService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/feedback-voluntario")
@CrossOrigin(origins = "*")
public class FeedbackVoluntarioController {

    private static final Logger log = LoggerFactory.getLogger(FeedbackVoluntarioController.class);

    @Autowired
    private FeedbackVoluntarioService service;

    @GetMapping("/all")
    public ResponseEntity<List<FeedbackVoluntario>> findAll() {
        System.out.println("[FeedbackVoluntarioController] GET /all");
        var resp = service.findAll();
        log.info("Listar feedbacks -> {} itens", resp.getBody() != null ? resp.getBody().size() : 0);
        return resp;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<FeedbackVoluntario>> findById(@Valid @PathVariable Long id) {
        System.out.println("[FeedbackVoluntarioController] GET /{id} id=" + id);
        log.info("Buscar feedback id={}", id);
        return service.findById(id);
    }

    @GetMapping("/voluntario/{voluntarioId}")
    public ResponseEntity<List<FeedbackVoluntario>> findByVoluntario(@PathVariable Long voluntarioId) {
        System.out.println("[FeedbackVoluntarioController] GET /voluntario/{id} id=" + voluntarioId);
        log.info("Listar feedbacks do voluntarioId={}", voluntarioId);
        return service.findByVoluntario(voluntarioId);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FeedbackVoluntario>> search(
            @RequestParam(required = false) String q,
            @RequestParam(required = false, name = "voluntarioId") Long voluntarioId) {
        long start = System.currentTimeMillis();
        System.out.println("[FeedbackVoluntarioController] GET /search q=" + q + " voluntarioId=" + voluntarioId);
        log.info("Buscar feedbacks por q='{}', voluntarioId={}", q, voluntarioId);
        var resp = service.search(q, voluntarioId);
        log.info("Search retornou {} itens ({}ms)",
                resp.getBody() != null ? resp.getBody().size() : 0,
                (System.currentTimeMillis() - start));
        return resp;
    }

    @PostMapping
    public ResponseEntity<FeedbackVoluntario> create(@Valid @RequestBody FeedbackVoluntario body) {
        System.out.println("[FeedbackVoluntarioController] POST / (create) voluntarioId="
                + (body.getVoluntario() != null ? body.getVoluntario().getId() : null));
        log.info("Criando feedback para voluntarioId={}",
                body.getVoluntario() != null ? body.getVoluntario().getId() : null);
        return new ResponseEntity<>(service.create(body), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackVoluntario> update(@PathVariable Long id, @Valid @RequestBody FeedbackVoluntario body) {
        System.out.println("[FeedbackVoluntarioController] PUT /{id} id=" + id);
        log.info("Atualizando feedback id={}", id);
        var resp = ResponseEntity.of(service.update(id, body));
        log.info("Atualização feedback id={} -> status={}", id, resp.getStatusCode());
        return resp;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        System.out.println("[FeedbackVoluntarioController] DELETE /{id} id=" + id);
        log.info("Excluindo feedback id={}", id);
        boolean deleted = service.delete(id);
        log.info("Exclusão feedback id={} -> {}", id, deleted ? "DELETADO" : "NÃO ENCONTRADO");
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
