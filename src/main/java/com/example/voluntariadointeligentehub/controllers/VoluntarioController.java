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

import com.example.voluntariadointeligentehub.entities.Voluntario;
import com.example.voluntariadointeligentehub.services.VoluntarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/voluntario")
public class VoluntarioController {

    @Autowired
    private VoluntarioService voluntarioService;

    @GetMapping("/all")
    public ResponseEntity<List<Voluntario>> findAll() {

        return voluntarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Voluntario>> findById(
            @Valid @PathVariable Long id) {
        return voluntarioService.findById(id);
    }

    @GetMapping("/nome")
    public ResponseEntity<Optional<Voluntario>> findByNome(
            @Valid @RequestParam String nome) {
        return voluntarioService.findByNome(nome);
    }

    @GetMapping("/email")
    public ResponseEntity<Optional<Voluntario>> findByEmail(
            @Valid @RequestParam String email) {
        return voluntarioService.findByEmail(email);
    }

    @PostMapping
    public ResponseEntity<Voluntario> create(@Valid @RequestBody Voluntario voluntario) {
        Voluntario createdVoluntario = voluntarioService.create(voluntario);
        return new ResponseEntity<>(createdVoluntario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voluntario> update(
            @PathVariable Long id, @Valid @RequestBody Voluntario voluntario) {
        return ResponseEntity.of(voluntarioService.update(id, voluntario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = voluntarioService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestParam String nome, @RequestParam String email, @RequestParam String password, @RequestParam String areaInteresse, @RequestParam String competencia) {
        return voluntarioService.register(nome, email, password, areaInteresse, competencia);
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
        return voluntarioService.login(email, password);
    }
}

