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

import com.example.voluntariadointeligentehub.entities.VagasVoluntarias;
import com.example.voluntariadointeligentehub.services.VagasVoluntariasService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/vagas-voluntarias")
public class VagasVoluntariasController {
    
    @Autowired
    private VagasVoluntariasService vagasVoluntariasService;

    @GetMapping("/all")
    public ResponseEntity<List<VagasVoluntarias>> findAll() {

        return vagasVoluntariasService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<VagasVoluntarias>> findById(@Valid @PathVariable Long id) {
        return vagasVoluntariasService.findById(id);
    }

    @GetMapping("/descricaoVaga")
    public ResponseEntity<Optional<VagasVoluntarias>> findByDescricaoVaga(@Valid @RequestParam String descricaoVaga) {
        return vagasVoluntariasService.findByDescricaoVaga(descricaoVaga);
    }

    @GetMapping("/area")
    public ResponseEntity<Optional<VagasVoluntarias>> findByArea(@Valid @RequestParam String area) {
        return vagasVoluntariasService.findByArea(area);
    }

    @GetMapping("/vagaAbrt")
    public ResponseEntity<Optional<VagasVoluntarias>> findByVagaAbrt(@Valid @RequestParam String vagaAbrt) {
        return vagasVoluntariasService.findByVagaAbrt(vagaAbrt);
    }

    @PostMapping
    public ResponseEntity<VagasVoluntarias> create(@Valid @RequestBody VagasVoluntarias voluntario) {
        VagasVoluntarias createdVoluntario = vagasVoluntariasService.create(voluntario);
        return new ResponseEntity<>(createdVoluntario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VagasVoluntarias> update(
            @PathVariable Long id, @Valid @RequestBody VagasVoluntarias voluntario) {
        return ResponseEntity.of(vagasVoluntariasService.update(id, voluntario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = vagasVoluntariasService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
