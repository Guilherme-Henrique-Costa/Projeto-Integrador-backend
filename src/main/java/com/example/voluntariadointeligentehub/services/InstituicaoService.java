package com.example.voluntariadointeligentehub.services;

import java.security.Key;
import java.util.*;

import com.example.voluntariadointeligentehub.entities.Voluntario;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.repositories.InstituicaoRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class InstituicaoService {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final Key jwtKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    private final long jwtExpirationMs = 86400000; // 24h

    @Transactional
    public ResponseEntity<List<Instituicao>> findAll() {
        return ResponseEntity.ok(instituicaoRepository.findAll());
    }

    @Transactional
    public ResponseEntity<Optional<Instituicao>> findById(Long id) {
        return ResponseEntity.ok(instituicaoRepository.findById(id));
    }

    @Transactional
    public ResponseEntity<Optional<Instituicao>> findByNome(String nome) {
        Optional<Instituicao> resultado = instituicaoRepository.findByNome(nome);
        return resultado.isPresent() ? ResponseEntity.ok(resultado) : ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<Optional<Instituicao>> findByEmail(String email) {
        Optional<Instituicao> resultado = instituicaoRepository.findByEmail(email);
        return resultado.isPresent() ? ResponseEntity.ok(resultado) : ResponseEntity.notFound().build();
    }

    @Transactional
    public Instituicao create(Instituicao instituicao) {
        try {
            instituicao.setPassword(passwordEncoder.encode(instituicao.getPassword()));
            return instituicaoRepository.save(instituicao);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar Instituicao: " + e.getMessage());
        }
    }

    @Transactional
    public Optional<Instituicao> update(Long id, Instituicao instituicaoDetails) {
        Optional<Instituicao> existing = instituicaoRepository.findById(id);
        if (existing.isPresent()) {
            Instituicao instituicao = existing.get();
            instituicao.setCnpj(instituicaoDetails.getCnpj());
            instituicao.setEmail(instituicaoDetails.getEmail());
            instituicao.setPassword(passwordEncoder.encode(instituicaoDetails.getPassword()));
            instituicao.setNome(instituicaoDetails.getNome());
            return Optional.of(instituicaoRepository.save(instituicao));
        }
        return Optional.empty();
    }

    @Transactional
    public boolean delete(Long id) {
        Optional<Instituicao> existente = instituicaoRepository.findById(id);
        if (existente.isPresent()) {
            instituicaoRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Transactional
    public String login(String email, String password) {
        Optional<Instituicao> instituicaoOpt = instituicaoRepository.findByEmail(email);
        if (instituicaoOpt.isPresent()) {
            Instituicao instituicao = instituicaoOpt.get();
            if (passwordEncoder.matches(password, instituicao.getPassword())) {
                return gerarJwtToken(instituicao); // retorna o token como string
            }
            throw new RuntimeException("Senha incorreta.");
        }
        throw new RuntimeException("Usuário não encontrado.");
    }


    @Transactional
    public ResponseEntity<Map<String, String>> register(Instituicao instituicao) {
        Optional<Instituicao> existente = instituicaoRepository.findByEmail(instituicao.getEmail());
        if (existente.isPresent()) {
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("message", "E-mail já registrado."));
        }

        try {
            instituicao.setPassword(passwordEncoder.encode(instituicao.getPassword()));
            instituicaoRepository.save(instituicao);
            return ResponseEntity.ok(Collections.singletonMap("message", "Usuário registrado com sucesso!"));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Collections.singletonMap("message", "Erro ao registrar: " + e.getMessage()));
        }
    }

    private String gerarJwtToken(Instituicao instituicao) {
        return Jwts.builder()
                .setSubject(instituicao.getEmail())
                .claim("nome", instituicao.getNome())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(jwtKey, SignatureAlgorithm.HS512)
                .compact();
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Voluntario>> getVoluntariosDaInstituicao(Long id) {
        Optional<Instituicao> instituicaoOpt = instituicaoRepository.findById(id);
        return instituicaoOpt.map(instituicao -> ResponseEntity.ok(instituicao.getVoluntarios()))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
