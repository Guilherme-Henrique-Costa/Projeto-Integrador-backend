package com.example.voluntariadointeligentehub.controllers;

import java.util.List;
import java.util.Optional;

import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.entities.PerfilInstituicao;
import com.example.voluntariadointeligentehub.services.InstituicaoService;
import com.example.voluntariadointeligentehub.services.PerfilInstituicaoService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/perfil-instituicao")
@CrossOrigin(origins = "*") // aplica para todos os endpoints deste controller
public class PerfilInstituicaoController {

    @Autowired
    private PerfilInstituicaoService perfilInstituicaoService;
    @Autowired
    private InstituicaoService instituicaoService;


    @GetMapping("/all")
    public ResponseEntity<List<PerfilInstituicao>> findAll() {
        return perfilInstituicaoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PerfilInstituicao>> findById(@Valid @PathVariable Long id) {
        return perfilInstituicaoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<PerfilInstituicao> create(@Valid @RequestBody PerfilInstituicao voluntario) {
        PerfilInstituicao created = perfilInstituicaoService.create(voluntario);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PerfilInstituicao> update(@PathVariable Long id, @Valid @RequestBody PerfilInstituicao voluntario) {
        return ResponseEntity.of(perfilInstituicaoService.update(id, voluntario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = perfilInstituicaoService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/email")
    public ResponseEntity<Instituicao> findByEmail(@RequestParam String email) {
        ResponseEntity<Optional<Instituicao>> resposta = instituicaoService.findByEmail(email);

        if (resposta == null || resposta.getBody() == null || resposta.getBody().isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(resposta.getBody().get());
    }

}
