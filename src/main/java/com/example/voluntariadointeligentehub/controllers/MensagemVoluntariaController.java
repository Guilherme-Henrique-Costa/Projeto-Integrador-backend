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

import com.example.voluntariadointeligentehub.entities.MensagemVoluntaria;
import com.example.voluntariadointeligentehub.services.MensagemVoluntariaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/mensagem-voluntaria")
public class MensagemVoluntariaController {
    
    @Autowired
    private MensagemVoluntariaService mensagemVoluntariaService;

    @GetMapping("/all")
    public ResponseEntity<List<MensagemVoluntaria>> findAll() {

        return mensagemVoluntariaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<MensagemVoluntaria>> findById(@Valid @PathVariable Long id) {
        return mensagemVoluntariaService.findById(id);
    }

    // @GetMapping("/descricaoVaga")
    // public ResponseEntity<Optional<MensagemVoluntaria>> findByDescricaoVaga(@Valid @RequestParam String descricaoVaga) {
    //     return MensagemVoluntariaService.findByDescricaoVaga(descricaoVaga);
    // }

    // @GetMapping("/area")
    // public ResponseEntity<Optional<MensagemVoluntaria>> findByArea(@Valid @RequestParam String area) {
    //     return MensagemVoluntariaService.findByArea(area);
    // }

    // @GetMapping("/vagaAbrt")
    // public ResponseEntity<Optional<MensagemVoluntaria>> findByVagaAbrt(@Valid @RequestParam String vagaAbrt) {
    //     return MensagemVoluntariaService.findByVagaAbrt(vagaAbrt);
    // }

    @PostMapping
    public ResponseEntity<MensagemVoluntaria> create(@Valid @RequestBody MensagemVoluntaria voluntario) {
        MensagemVoluntaria createdVoluntario = mensagemVoluntariaService.create(voluntario);
        return new ResponseEntity<>(createdVoluntario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MensagemVoluntaria> update(
            @PathVariable Long id, @Valid @RequestBody MensagemVoluntaria voluntario) {
        return ResponseEntity.of(mensagemVoluntariaService.update(id, voluntario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = mensagemVoluntariaService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
