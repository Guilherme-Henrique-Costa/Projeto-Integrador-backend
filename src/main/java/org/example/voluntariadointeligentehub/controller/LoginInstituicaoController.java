package org.example.voluntariadointeligentehub.controller;

import org.example.voluntariadointeligentehub.entity.PerfilInstituicao;
import org.example.voluntariadointeligentehub.service.LoginInstituicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/instituicao")
public class LoginInstituicaoController {

    @Autowired
    private LoginInstituicaoService loginInstituicaoService;

    // Endpoint de login
    @PostMapping("/login")
    public ResponseEntity<?> loginInstituicao(
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        PerfilInstituicao instituicao = loginInstituicaoService.autenticar(email, password);

        if (instituicao != null) {
            // Retorna os dados do usuário, sem a senha
            return ResponseEntity.ok(instituicao);
        } else {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
