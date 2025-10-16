package com.example.voluntariadointeligentehub.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.voluntariadointeligentehub.entities.Voluntario;
import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.services.InstituicaoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/instituicao")
@CrossOrigin(origins = "*")
public class InstituicaoController {

    private static final Logger log = LoggerFactory.getLogger(InstituicaoController.class);

    @Autowired
    private InstituicaoService instituicaoService;

    @GetMapping("/verificar-email")
    public ResponseEntity<Map<String, Object>> verificarEmail(@RequestParam String email) {
        System.out.println("[InstituicaoController] GET /verificar-email?email=" + email);
        log.info("Verificando e-mail da instituição '{}'", email);

        Optional<Instituicao> opt = instituicaoService.buscarPorEmail(email);
        Map<String, Object> resposta = new HashMap<>();

        if (opt.isPresent()) {
            Instituicao inst = opt.get();
            resposta.put("existe", true);
            resposta.put("id", inst.getId());
            resposta.put("nome", inst.getNome());
            return ResponseEntity.ok(resposta);
        } else {
            resposta.put("existe", false);
            return ResponseEntity.ok(resposta);
        }
    }

    @PostMapping("/redefinir-senha")
    public ResponseEntity<Map<String, Object>> redefinirSenha(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String novaSenha = body.get("senha");

        System.out.println("[InstituicaoController] POST /redefinir-senha email=" + email);
        log.info("Redefinindo senha para email='{}'", email);

        try {
            boolean sucesso = instituicaoService.redefinirSenha(email, novaSenha);
            Map<String, Object> resposta = new HashMap<>();
            resposta.put("sucesso", sucesso);
            resposta.put("mensagem", sucesso
                    ? "Senha redefinida com sucesso."
                    : "Instituição não encontrada para o e-mail informado.");

            return ResponseEntity.ok(resposta);

        } catch (Exception e) {
            log.error("Erro ao redefinir senha: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError()
                    .body(Map.of("erro", "Falha ao redefinir senha."));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Instituicao>> findAll() {
        System.out.println("[InstituicaoController] GET /all");
        var resp = instituicaoService.findAll();
        log.info("Listar todas as instituições -> {} itens", resp.getBody() != null ? resp.getBody().size() : 0);
        return resp;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Instituicao>> findById(@Valid @PathVariable Long id) {
        System.out.println("[InstituicaoController] GET /{id} id=" + id);
        log.info("Buscar instituição por id={}", id);
        return instituicaoService.findById(id);
    }

    @GetMapping("/nome")
    public ResponseEntity<Optional<Instituicao>> findByNome(@Valid @RequestParam String nome) {
        System.out.println("[InstituicaoController] GET /nome?nome=" + nome);
        log.info("Buscar instituição por nome='{}'", nome);
        return instituicaoService.findByNome(nome);
    }

    @GetMapping("/email")
    public ResponseEntity<Instituicao> findByEmail(@RequestParam String email) {
        System.out.println("[InstituicaoController] GET /email?email=" + email);
        log.info("Buscar instituição por email='{}'", email);
        ResponseEntity<Optional<Instituicao>> resposta = instituicaoService.findByEmail(email);
        if (resposta.getBody() == null || resposta.getBody().isEmpty()) {
            log.warn("Instituição não encontrada para email='{}'", email);
            return ResponseEntity.notFound().build();
        }
        log.info("Instituição encontrada id={}", resposta.getBody().get().getId());
        return ResponseEntity.ok(resposta.getBody().get());
    }

    @PostMapping
    public ResponseEntity<Instituicao> create(@Valid @RequestBody Instituicao instituicao) {
        System.out.println("[InstituicaoController] POST / (create) nome=" + instituicao.getNome());
        log.info("Criando instituição nome='{}', email='{}'", instituicao.getNome(), instituicao.getEmail());
        Instituicao createdInstituicao = instituicaoService.create(instituicao);
        log.info("Instituição criada id={}", createdInstituicao.getId());
        return new ResponseEntity<>(createdInstituicao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instituicao> update(@PathVariable Long id, @Valid @RequestBody Instituicao instituicao) {
        System.out.println("[InstituicaoController] PUT /{id} id=" + id);
        log.info("Atualizando instituição id={}", id);
        var resp = ResponseEntity.of(instituicaoService.update(id, instituicao));
        log.info("Atualização concluída id={} -> status={}", id, resp.getStatusCode());
        return resp;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        System.out.println("[InstituicaoController] DELETE /{id} id=" + id);
        log.info("Excluindo instituição id={}", id);
        boolean deleted = instituicaoService.delete(id);
        log.info("Exclusão id={} -> {}", id, deleted ? "DELETADO" : "NÃO ENCONTRADO");
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody Instituicao instituicao) {
        System.out.println("[InstituicaoController] POST /register email=" + instituicao.getEmail());
        log.info("Registrando instituição email='{}'", instituicao.getEmail());
        return instituicaoService.register(instituicao);
    }

    @GetMapping("/{id}/voluntarios")
    public ResponseEntity<List<Voluntario>> getVoluntarios(@PathVariable Long id) {
        System.out.println("[InstituicaoController] GET /{id}/voluntarios id=" + id);
        log.info("Listando voluntários da instituição id={}", id);
        return instituicaoService.getVoluntariosDaInstituicao(id);
    }
}
