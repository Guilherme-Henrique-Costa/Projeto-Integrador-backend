package org.example.voluntariadointeligentehub.controller;

import jakarta.validation.Valid;
import org.example.voluntariadointeligentehub.entity.Voluntario;
import org.example.voluntariadointeligentehub.service.CadastroVoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/voluntarios")
@Validated  // Habilita validação para o controller
public class CadastroVoluntarioController {

    @Autowired
    private CadastroVoluntarioService cadastroVoluntarioService;

    @PostMapping
    public ResponseEntity<?> cadastrarVoluntario(@Valid @RequestBody Voluntario voluntario) {
        try {
            // Chama o serviço para salvar o voluntário
            Voluntario voluntarioCadastrado = cadastroVoluntarioService.cadastrarVoluntario(voluntario);
            return ResponseEntity.ok(voluntarioCadastrado);
        } catch (IllegalArgumentException e) {
            // Retorna um erro 400 (Bad Request) com uma mensagem específica de erro
            return ResponseEntity.badRequest().body(Map.of("erro", e.getMessage()));
        } catch (Exception e) {
            // Retorna um erro 500 (Internal Server Error) se houver um problema no servidor
            return ResponseEntity.status(500).body(Map.of("erro", "Erro no servidor. Tente novamente mais tarde."));
        }
    }
}
