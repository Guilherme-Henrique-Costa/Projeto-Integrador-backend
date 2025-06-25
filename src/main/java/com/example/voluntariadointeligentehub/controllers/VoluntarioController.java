package com.example.voluntariadointeligentehub.controllers;

import com.example.voluntariadointeligentehub.entities.Voluntario;
import com.example.voluntariadointeligentehub.services.VoluntarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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
    public ResponseEntity<Optional<Voluntario>> findById(@Valid @PathVariable Long id) {
        return voluntarioService.findById(id);
    }

    @GetMapping("/nome")
    public ResponseEntity<Optional<Voluntario>> findByNome(@Valid @RequestParam String nome) {
        return voluntarioService.findByNome(nome);
    }

    @GetMapping("/email")
    public ResponseEntity<Optional<Voluntario>> findByEmail(@Valid @RequestParam String email) {
        return voluntarioService.findByEmailInstitucional(email);
    }

    @PostMapping
    public ResponseEntity<Voluntario> create(@Valid @RequestBody Voluntario voluntario) {
        Voluntario created = voluntarioService.create(voluntario);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Voluntario> update(@PathVariable Long id, @Valid @RequestBody Voluntario voluntario) {
        return ResponseEntity.of(voluntarioService.update(id, voluntario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return voluntarioService.delete(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/semana")
    public ResponseEntity<List<Voluntario>> findByDisponibilidade(@RequestParam String turno) {
        return ResponseEntity.ok(voluntarioService.findByDisponibilidadeSemanal(turno));
    }
    @PostMapping("/login")
    public ResponseEntity<Voluntario> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("emailInstitucional");
        String password = loginData.get("password");

        Optional<Voluntario> voluntarioOpt = voluntarioService.login(email, password);
        return voluntarioOpt
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.FORBIDDEN).build());
    }
}
