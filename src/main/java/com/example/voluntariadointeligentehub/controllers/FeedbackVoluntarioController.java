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

import com.example.voluntariadointeligentehub.entities.FeedbackVoluntario;
import com.example.voluntariadointeligentehub.services.FeedbackVoluntarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/feedback-voluntario")
public class FeedbackVoluntarioController {
    
    @Autowired
    private FeedbackVoluntarioService feedbackVoluntarioService;

    @GetMapping("/all")
    public ResponseEntity<List<FeedbackVoluntario>> findAll() {

        return feedbackVoluntarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<FeedbackVoluntario>> findById(@Valid @PathVariable Long id) {
        return feedbackVoluntarioService.findById(id);
    }

    // @GetMapping("/descricaoVaga")
    // public ResponseEntity<Optional<FeedbackVoluntario>> findByDescricaoVaga(@Valid @RequestParam String descricaoVaga) {
    //     return FeedbackVoluntarioService.findByDescricaoVaga(descricaoVaga);
    // }

    // @GetMapping("/area")
    // public ResponseEntity<Optional<FeedbackVoluntario>> findByArea(@Valid @RequestParam String area) {
    //     return FeedbackVoluntarioService.findByArea(area);
    // }

    // @GetMapping("/vagaAbrt")
    // public ResponseEntity<Optional<FeedbackVoluntario>> findByVagaAbrt(@Valid @RequestParam String vagaAbrt) {
    //     return FeedbackVoluntarioService.findByVagaAbrt(vagaAbrt);
    // }

    @PostMapping
    public ResponseEntity<FeedbackVoluntario> create(@Valid @RequestBody FeedbackVoluntario voluntario) {
        FeedbackVoluntario createdVoluntario = feedbackVoluntarioService.create(voluntario);
        return new ResponseEntity<>(createdVoluntario, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeedbackVoluntario> update(
            @PathVariable Long id, @Valid @RequestBody FeedbackVoluntario voluntario) {
        return ResponseEntity.of(feedbackVoluntarioService.update(id, voluntario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = feedbackVoluntarioService.delete(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
