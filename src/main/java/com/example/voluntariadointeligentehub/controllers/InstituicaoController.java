package com.example.voluntariadointeligentehub.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.services.InstituicaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/instituicao")
public class InstituicaoController {
    
    @Autowired
    private InstituicaoService instituicaoService;

    @GetMapping("/all")
    public ResponseEntity<List<Instituicao>> findAll() {

        return instituicaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Instituicao>> findById(@Valid @PathVariable Long id) {
        return instituicaoService.findById(id);
    }

    @GetMapping("/nome")
    public ResponseEntity<Optional<Instituicao>> findByNome(
            @Valid @RequestParam String nome) {
        return instituicaoService.findByNome(nome);
    }

    @GetMapping("/email")
    public ResponseEntity<Optional<Instituicao>> findByEmail(
            @Valid @RequestParam String email) {
        return instituicaoService.findByEmail(email);
    }

    @PostMapping
    public ResponseEntity<Instituicao> create(@Valid @RequestBody Instituicao instituicao) {
        Instituicao createdInstituicao = instituicaoService.create(instituicao);
        return new ResponseEntity<>(createdInstituicao, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instituicao> update(
            @PathVariable Long id, @Valid @RequestBody Instituicao instituicao) {
        return ResponseEntity.of(instituicaoService.update(id, instituicao));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = instituicaoService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String nome, @RequestParam String email,@RequestParam Integer cnpj, @RequestParam String areaInstituicao,  @RequestParam String password, @RequestParam String descricao) {
        return instituicaoService.register(nome, email, cnpj, areaInstituicao, password, descricao);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        return instituicaoService.login(email, password);
    }
}
