package com.example.voluntariadointeligentehub.controllers;


import com.example.voluntariadointeligentehub.entities.Voluntario;
import com.example.voluntariadointeligentehub.services.VoluntarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
}

