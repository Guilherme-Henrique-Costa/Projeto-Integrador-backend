package com.example.voluntariadointeligentehub.controllers;

import com.example.voluntariadointeligentehub.dto.AdminAuthResponseDTO;
import com.example.voluntariadointeligentehub.dto.AdminLoginResponseDTO;
import com.example.voluntariadointeligentehub.dto.AdminRegisterRequestDTO;
import com.example.voluntariadointeligentehub.dto.LoginRequestDTO;
import com.example.voluntariadointeligentehub.services.AdminAuthService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/auth")
@CrossOrigin(origins = "*", allowCredentials = "false")
public class AuthAdminController {


    private static final Logger LOG = LoggerFactory.getLogger(AuthAdminController.class);


    private final AdminAuthService adminAuthService;


    public AuthAdminController(AdminAuthService adminAuthService) {
        this.adminAuthService = adminAuthService;
    }


    @PostMapping("/register")
    public ResponseEntity<AdminAuthResponseDTO> register(@Valid @RequestBody AdminRegisterRequestDTO req) {
        LOG.debug("[HTTP][POST] /api/v1/admin/auth/register body.email={}", req.getEmail());
        AdminAuthResponseDTO resp = adminAuthService.register(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<AdminAuthResponseDTO> authenticate(@Valid @RequestBody LoginRequestDTO req) {
        LOG.debug("[HTTP][POST] /api/v1/admin/auth/authenticate body.email={}", req.getEmail());
        AdminAuthResponseDTO resp = adminAuthService.authenticate(req);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/login")
    public ResponseEntity<AdminLoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO req) {
        LOG.debug("[HTTP][POST] /api/v1/admin/auth/login email={}", req.getEmail());
        return ResponseEntity.ok(adminAuthService.login(req));
    }
}