package com.example.voluntariadointeligentehub.controllers;

import com.example.voluntariadointeligentehub.dto.LoginRequestDTO;
import com.example.voluntariadointeligentehub.dto.LoginResponseDTO;
import com.example.voluntariadointeligentehub.entities.Voluntario;
import com.example.voluntariadointeligentehub.jwt.JwtUtil;
import com.example.voluntariadointeligentehub.services.VoluntarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final VoluntarioService voluntarioService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(), loginRequest.getSenha()
                    )
            );

            Voluntario voluntario = voluntarioService.buscarPorEmail(loginRequest.getEmail());
            String token = jwtUtil.gerarToken(voluntario);

            return ResponseEntity.ok(new LoginResponseDTO(token, voluntario));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body("Credenciais inválidas");
        }
    }
}
