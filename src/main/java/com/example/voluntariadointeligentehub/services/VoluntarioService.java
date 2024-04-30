package com.example.voluntariadointeligentehub.services;

import com.example.voluntariadointeligentehub.entities.Voluntario;
import com.example.voluntariadointeligentehub.repositories.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VoluntarioService {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

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

            return resultado.isPresent() ? ResponseEntity.ok(resultado) :
                    ResponseEntity.notFound().build();
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

            return resultado.isPresent() ? ResponseEntity.ok(resultado) :
                    ResponseEntity.notFound().build();
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
}
