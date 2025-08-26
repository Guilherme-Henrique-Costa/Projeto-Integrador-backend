package com.example.voluntariadointeligentehub.controllers;

import com.example.voluntariadointeligentehub.dto.LoginInstituicaoResponseDTO;
import com.example.voluntariadointeligentehub.dto.LoginRequestDTO;
import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.services.InstituicaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/instituicao")
@CrossOrigin(origins = "*")
public class AuthInstituicaoController {

    private static final Logger log = LoggerFactory.getLogger(AuthInstituicaoController.class);

    @Autowired
    private InstituicaoService instituicaoService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        final long start = System.currentTimeMillis();
        System.out.println("[AuthInstituicaoController] POST /login - email=" + loginRequest.getEmail());
        log.info("Auth login attempt for email='{}'", loginRequest.getEmail());

        try {
            String token = instituicaoService.login(loginRequest.getEmail(), loginRequest.getSenha());
            Instituicao instituicao = instituicaoService.buscarPorEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("Instituição não encontrada"));

            long took = System.currentTimeMillis() - start;
            log.info("Auth login success for email='{}', instituicaoId={}, took={}ms",
                    loginRequest.getEmail(), instituicao.getId(), took);
            System.out.println("[AuthInstituicaoController] LOGIN OK - instituicaoId=" + instituicao.getId() + " (" + took + "ms)");

            // Não logar o token em si
            log.debug("JWT generated (length={} chars)", token != null ? token.length() : 0);

            return ResponseEntity.ok(new LoginInstituicaoResponseDTO(
                    token, instituicao.getId(), instituicao.getNome()
            ));
        } catch (RuntimeException ex) {
            long took = System.currentTimeMillis() - start;
            log.warn("Auth login failed for email='{}' ({}ms): {}", loginRequest.getEmail(), took, ex.getMessage());
            System.out.println("[AuthInstituicaoController] LOGIN FAIL - email=" + loginRequest.getEmail() + " (" + took + "ms)");
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
