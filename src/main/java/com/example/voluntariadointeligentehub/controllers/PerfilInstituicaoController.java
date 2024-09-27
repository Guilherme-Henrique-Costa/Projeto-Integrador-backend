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
import org.springframework.web.bind.annotation.RestController;

import com.example.voluntariadointeligentehub.entities.PerfilInstituicao;
import com.example.voluntariadointeligentehub.services.PerfilInstituicaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/perfil-instituicao")
public class PerfilInstituicaoController {
    
    @Autowired
    private PerfilInstituicaoService PerfilInstituicaoService;

    @GetMapping("/all")
    public ResponseEntity<List<PerfilInstituicao>> findAll() {

        return PerfilInstituicaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PerfilInstituicao>> findById(@Valid @PathVariable Long id) {
        return PerfilInstituicaoService.findById(id);
    }

    // @GetMapping("/descricaoVaga")
    // public ResponseEntity<Optional<PerfilInstituicao>> findByDescricaoVaga(@Valid @RequestParam String descricaoVaga) {
    //     return PerfilInstituicaoService.findByDescricaoVaga(descricaoVaga);
    // }

    // @GetMapping("/area")
    // public ResponseEntity<Optional<PerfilInstituicao>> findByArea(@Valid @RequestParam String area) {
    //     return PerfilInstituicaoService.findByArea(area);
    // }

    // @GetMapping("/vagaAbrt")
    // public ResponseEntity<Optional<PerfilInstituicao>> findByVagaAbrt(@Valid @RequestParam String vagaAbrt) {
    //     return PerfilInstituicaoService.findByVagaAbrt(vagaAbrt);
    // }

    @PostMapping
    public ResponseEntity<PerfilInstituicao> create(@Valid @RequestBody PerfilInstituicao voluntario) {
        PerfilInstituicao createdVoluntario = PerfilInstituicaoService.create(voluntario);
        return new ResponseEntity<>(createdVoluntario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfilInstituicao> update(
            @PathVariable Long id, @Valid @RequestBody PerfilInstituicao voluntario) {
        return ResponseEntity.of(PerfilInstituicaoService.update(id, voluntario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = PerfilInstituicaoService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
