package com.example.voluntariadointeligentehub.services;


import com.example.voluntariadointeligentehub.dto.AdminAuthResponseDTO;
import com.example.voluntariadointeligentehub.dto.AdminLoginResponseDTO;
import com.example.voluntariadointeligentehub.dto.AdminRegisterRequestDTO;
import com.example.voluntariadointeligentehub.dto.LoginRequestDTO;
import com.example.voluntariadointeligentehub.entities.Admin;
import com.example.voluntariadointeligentehub.entities.AdminRole;
import com.example.voluntariadointeligentehub.jwt.JwtUtil;
import com.example.voluntariadointeligentehub.repositories.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminAuthService {


    private static final Logger LOG = LoggerFactory.getLogger(AdminAuthService.class);


    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    public AdminAuthService(AdminRepository adminRepository,
                            PasswordEncoder passwordEncoder,
                            AuthenticationManager authenticationManager,
                            JwtUtil jwtUtil) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @Transactional
    public AdminAuthResponseDTO register(AdminRegisterRequestDTO req) {
        LOG.info("[ADMIN][REGISTER] Tentando cadastrar admin: email={} funcao={}", req.getEmail(), req.getFuncao());
        if (adminRepository.existsByEmail(req.getEmail())) {
            LOG.warn("[ADMIN][REGISTER] E-mail já cadastrado: {}", req.getEmail());
            throw new IllegalArgumentException("E-mail já cadastrado");
        }
        try {
            Admin admin = new Admin();
            admin.setNome(req.getNome());
            admin.setEmail(req.getEmail());
            admin.setSenha(passwordEncoder.encode(req.getSenha())); // ⚠️ nunca logar senha
            if (req.getFuncao() != null && !req.getFuncao().isBlank()) {
                try {
                    admin.setFuncao(AdminRole.valueOf(req.getFuncao()));
                } catch (Exception e) {
                    LOG.warn("[ADMIN][REGISTER] Função inválida '{}', aplicando padrão {}", req.getFuncao(), AdminRole.ADMIN_CEUB);
                    admin.setFuncao(AdminRole.ADMIN_CEUB);
                }
            }
            Admin saved = adminRepository.save(admin);
            String token = jwtUtil.gerarToken(saved); // opcional
            LOG.info("[ADMIN][REGISTER] Sucesso: id={} email={} funcao={}", saved.getId(), saved.getEmail(), saved.getFuncao());
            return new AdminAuthResponseDTO(saved.getId(), saved.getNome(), saved.getEmail(), saved.getFuncao().name(), token, "Cadastro realizado com sucesso");
        } catch (DataIntegrityViolationException ex) {
            LOG.error("[ADMIN][REGISTER] Violação de integridade ao cadastrar email={}: {}", req.getEmail(), ex.getMessage());
            throw new IllegalArgumentException("E-mail já cadastrado");
        } catch (RuntimeException ex) {
            LOG.error("[ADMIN][REGISTER] Erro inesperado ao cadastrar email={}", req.getEmail(), ex);
            throw ex;
        }
    }


    @Transactional(readOnly = true)
    public AdminAuthResponseDTO authenticate(LoginRequestDTO req) {
        LOG.info("[ADMIN][AUTH] email={}", req.getEmail());
        Admin admin = adminRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas"));
        if (!passwordEncoder.matches(req.getSenha(), admin.getSenha())) {
            LOG.warn("[ADMIN][AUTH] senha incorreta para email={}", req.getEmail());
            throw new IllegalArgumentException("Credenciais inválidas");
        }
        String token = jwtUtil.gerarToken(admin);
        return new AdminAuthResponseDTO(admin.getId(), admin.getNome(), admin.getEmail(), admin.getFuncao().name(), token, "Autenticado");
    }


    @Transactional(readOnly = true)
    public AdminLoginResponseDTO login(LoginRequestDTO req) {
        LOG.info("[ADMIN][LOGIN] email={}", req.getEmail());
        Admin admin = adminRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Credenciais inválidas"));
        if (!passwordEncoder.matches(req.getSenha(), admin.getSenha())) {
            LOG.warn("[ADMIN][LOGIN] senha incorreta para email={}", req.getEmail());
            throw new IllegalArgumentException("Credenciais inválidas");
        }
        String token = jwtUtil.gerarToken(admin);
        return new AdminLoginResponseDTO(token, admin.getId(), admin.getNome(), admin.getFuncao() != null ? admin.getFuncao().name() : null);
    }
}