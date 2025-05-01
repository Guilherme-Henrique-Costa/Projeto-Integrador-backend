package com.example.voluntariadointeligentehub.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.voluntariadointeligentehub.entities.Voluntario;
import com.example.voluntariadointeligentehub.repositories.VoluntarioRepository;

@Service
public class VoluntarioService {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public ResponseEntity<List<Voluntario>> findAll() {
        try {
            List<Voluntario> resultado = voluntarioRepository.findAll();

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println("Erro ao buscar voluntarios: " + e.getMessage());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar os voluntarios.");
        }
    }

    @Transactional
    public ResponseEntity<Optional<Voluntario>> findById(Long id) {
        try {
            Optional<Voluntario> resultado = voluntarioRepository.findById(id);

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println(
                    "Erro ao buscar voluntario por id: " + e.getMessage() + e.getCause());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar voluntario por id");
        }
    }

    @Transactional
    public ResponseEntity<Optional<Voluntario>> findByNome(String nome) {
        try {
            Optional<Voluntario> resultado = voluntarioRepository.findByNome(nome);

            return resultado.isPresent() ? ResponseEntity.ok(resultado)
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.err.println(
                    "Erro ao buscar voluntario por nome: " + e.getMessage() + e.getCause());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar voluntario por nome");
        }
    }

    @Transactional
    public ResponseEntity<Optional<Voluntario>> findByEmail(String email) {
        try {
            Optional<Voluntario> resultado = voluntarioRepository.findByEmail(email);

            return resultado.isPresent() ? ResponseEntity.ok(resultado)
                    : ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.err.println(
                    "Erro ao buscar voluntario por email: " + e.getMessage() + e.getCause());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar voluntario por email");
        }
    }

    @Transactional
    public ResponseEntity<Optional<Voluntario>> findByPassword(String password) {
        try {
            Optional<Voluntario> resultado = voluntarioRepository.findByPassword(password);

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println(
                    "Erro ao buscar voluntario por senha: " + e.getMessage() + e.getCause());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar voluntario por senha");
        }
    }

    @Transactional
public Voluntario create(Voluntario voluntario) {
    try {
        voluntario.setPassword(passwordEncoder.encode(voluntario.getPassword()));

        return voluntarioRepository.save(voluntario);
    } catch (Exception e) {
        throw new RuntimeException("Erro ao criar voluntario: " + e.getMessage());
    }
}

    @Transactional
    public Optional<Voluntario> update(Long id, Voluntario voluntarioDetails) {
        try {
            Optional<Voluntario> existingVoluntarioOpt = voluntarioRepository.findById(id);
            if (existingVoluntarioOpt.isPresent()) {
                Voluntario voluntario = existingVoluntarioOpt.get();
                voluntario.setNome(voluntarioDetails.getNome());
                voluntario.setEmail(voluntarioDetails.getEmail());
                voluntario.setPassword(passwordEncoder.encode(voluntarioDetails.getPassword()));
                voluntario.setNascimento(voluntarioDetails.getNascimento());
                voluntario.setInteresse(voluntarioDetails.getInteresse());
                voluntario.setCompetencia(voluntarioDetails.getCompetencia());
                voluntario.setDescricaoVaga(voluntarioDetails.getDescricaoVaga());

                return Optional.of(voluntarioRepository.save(voluntario));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar voluntario: " + e.getMessage());
        }
    }

    @Transactional
    public boolean delete(Long id) {
        try {
            Optional<Voluntario> existingVoluntario = voluntarioRepository.findById(id);
            if (existingVoluntario.isPresent()) {
                voluntarioRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar voluntario: " + e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<String> register(String nome, String email, String password, String areaInteresse, String competencia) {
        try {
            Optional<Voluntario> existingVoluntario = voluntarioRepository.findByEmail(email);
            if (existingVoluntario.isPresent()) {
                return ResponseEntity.badRequest().body("E-mail já registrado.");
            }
            Voluntario voluntario = new Voluntario();
            voluntario.setNome(nome);
            voluntario.setEmail(email);
            voluntario.setPassword(passwordEncoder.encode(password));
            voluntario.setInteresse(areaInteresse);
            voluntario.setCompetencia(competencia);

            voluntarioRepository.save(voluntario);
            return ResponseEntity.ok("Usuário registrado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao registrar usuário: " + e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<String> login(String email, String password) {
        try {
            Optional<Voluntario> voluntarioOpt = voluntarioRepository.findByEmail(email);
            if (voluntarioOpt.isPresent()) {
                Voluntario voluntario = voluntarioOpt.get();

                if (passwordEncoder.matches(password, voluntario.getPassword())) {
                    return ResponseEntity.ok("Login bem-sucedido!");
                } else {
                    return ResponseEntity.badRequest().body("Senha incorreta.");
                }
            } else {
                return ResponseEntity.badRequest().body("Usuário não encontrado.");
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao realizar login: " + e.getMessage());
        }
    }

}
