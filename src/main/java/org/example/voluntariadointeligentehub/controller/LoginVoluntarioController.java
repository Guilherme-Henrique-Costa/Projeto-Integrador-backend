package org.example.voluntariadointeligentehub.controller;

import org.example.voluntariadointeligentehub.entity.Voluntario;
import org.example.voluntariadointeligentehub.service.LoginVoluntarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/voluntario")
public class LoginVoluntarioController {

    @Autowired
    private LoginVoluntarioService loginVoluntarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> loginData) {
        String email = loginData.get("email");
        String password = loginData.get("password");

        System.out.println("Email recebido: " + email);
        System.out.println("Senha recebida: " + password);

        try {
            Voluntario voluntario = loginVoluntarioService.loginVoluntario(email, password);

            if (voluntario != null) {
                // Criar resposta de sucesso com os campos necessários
                Map<String, Object> response = new HashMap<>();
                response.put("id", voluntario.getId());
                response.put("email", voluntario.getEmail());
                response.put("nome", voluntario.getNome());  // Adiciona o campo "nome"
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body(Map.of("erro", "Credenciais inválidas!"));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(Map.of("erro", e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("erro", "Erro no servidor: " + e.getMessage()));
        }
    }


    // Método para logout, invalidando a sessão
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return ResponseEntity.ok(Map.of("mensagem", "Logout realizado com sucesso!"));
    }
}
