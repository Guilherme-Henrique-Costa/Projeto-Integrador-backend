package com.example.voluntariadointeligentehub.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.example.voluntariadointeligentehub.entities.Voluntario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.services.InstituicaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/instituicao")
public class InstituicaoController {

    @Autowired
    private InstituicaoService instituicaoService;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @GetMapping("/all")
    public ResponseEntity<List<Instituicao>> findAll() {
        return instituicaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Instituicao>> findById(@Valid @PathVariable Long id) {
        return instituicaoService.findById(id);
    }

    @GetMapping("/nome")
    public ResponseEntity<Optional<Instituicao>> findByNome(@Valid @RequestParam String nome) {
        return instituicaoService.findByNome(nome);
    }

    @GetMapping("/email")
    public ResponseEntity<Optional<Instituicao>> findByEmail(@Valid @RequestParam String email) {
        return instituicaoService.findByEmail(email);
    }

    @PostMapping
    public ResponseEntity<Instituicao> create(@Valid @RequestBody Instituicao instituicao) {
        Instituicao createdInstituicao = instituicaoService.create(instituicao);
        return new ResponseEntity<>(createdInstituicao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instituicao> update(@PathVariable Long id, @Valid @RequestBody Instituicao instituicao) {
        return ResponseEntity.of(instituicaoService.update(id, instituicao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = instituicaoService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody Instituicao instituicao) {
        return instituicaoService.register(instituicao);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String password = request.get("password");

        System.out.println("📩 Login recebido:");
        System.out.println("Email: " + email);
        System.out.println("Password (raw): " + password);

        Optional<Instituicao> optional = instituicaoService.buscarPorEmail(email);

        if (optional.isEmpty()) {
            System.out.println("❌ Email não encontrado: " + email);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("erro", "Email não encontrado"));
        }

        Instituicao instituicao = optional.get();

        boolean senhaConfere = encoder.matches(password, instituicao.getPassword());
        System.out.println("🔍 Resultado da verificação de senha: " + senhaConfere);

        if (!senhaConfere) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("erro", "Senha incorreta"));
        }

        String token = instituicaoService.login(email, password); // gere o token aqui
        System.out.println("✅ Login bem-sucedido. Token gerado.");

        return ResponseEntity.ok(Collections.singletonMap("token", token));
    }

    @GetMapping("/{id}/voluntarios")
    public ResponseEntity<List<Voluntario>> getVoluntarios(@PathVariable Long id) {
        return instituicaoService.getVoluntariosDaInstituicao(id);
    }
}
