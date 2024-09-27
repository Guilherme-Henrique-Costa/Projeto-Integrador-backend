package com.example.voluntariadointeligentehub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.voluntariadointeligentehub.entities.VagasVoluntarias;
import com.example.voluntariadointeligentehub.repositories.VagasVoluntariasRepository;

@Service
public class VagasVoluntariasService {
    
    @Autowired
    private VagasVoluntariasRepository vagasVoluntariasRepository;

    @Transactional
    public ResponseEntity<List<VagasVoluntarias>> findAll() {
        try {
            List<VagasVoluntarias> resultado = vagasVoluntariasRepository.findAll();

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println("Erro ao buscar vagas voluntarias: " + e.getMessage());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar os VagasVoluntariass.");
        }
    }

    @Transactional
    public ResponseEntity<Optional<VagasVoluntarias>> findById(Long id) {
        try {
            Optional<VagasVoluntarias> resultado = vagasVoluntariasRepository.findById(id);

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println(
                    "Erro ao buscar VagasVoluntarias por id: " + e.getMessage() + e.getCause());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar vagas voluntarias por id");
        }
    }

    @Transactional
    public ResponseEntity<Optional<VagasVoluntarias>> findByDescricaoVaga(String descricaoVaga) {
        try {
            Optional<VagasVoluntarias> resultado = vagasVoluntariasRepository.findByDescricaoVaga(descricaoVaga);

            return resultado.isPresent() ? ResponseEntity.ok(resultado) :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.err.println(
                    "Erro ao buscar VagasVoluntarias por nome: " + e.getMessage() + e.getCause());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar VagasVoluntarias por nome");
        }
    }

    @Transactional
    public ResponseEntity<Optional<VagasVoluntarias>> findByArea(String area) {
        try {
            Optional<VagasVoluntarias> resultado = vagasVoluntariasRepository.findByArea(area);

            return resultado.isPresent() ? ResponseEntity.ok(resultado) :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.err.println(
                    "Erro ao buscar VagasVoluntarias por email: " + e.getMessage() + e.getCause());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar VagasVoluntarias por email");
        }
    }


    @Transactional
    public ResponseEntity<Optional<VagasVoluntarias>> findByVagaAbrt(String vagaAbrt) {
        try {
            Optional<VagasVoluntarias> resultado = vagasVoluntariasRepository.findByVagaAbrt(vagaAbrt);

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println(
                    "Erro ao buscar VagasVoluntarias por senha: " + e.getMessage() + e.getCause());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar VagasVoluntarias por senha");
        }
    }

    @Transactional
    public VagasVoluntarias create(VagasVoluntarias vagasVoluntarias) {
        try {
            return vagasVoluntariasRepository.save(vagasVoluntarias);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar VagasVoluntarias: " + e.getMessage());
        }
    }

    @Transactional
    public Optional<VagasVoluntarias> update(Long id, VagasVoluntarias VagasVoluntariasDetails) {
        try {
            Optional<VagasVoluntarias> existingVagasVoluntarias = vagasVoluntariasRepository.findById(id);
            if (existingVagasVoluntarias.isPresent()) {
                VagasVoluntarias VagasVoluntarias = existingVagasVoluntarias.get();
                VagasVoluntarias.setArea(VagasVoluntariasDetails.getArea());
                VagasVoluntarias.setVagaAbrt(VagasVoluntariasDetails.getVagaAbrt());
                VagasVoluntarias.setVlt(VagasVoluntariasDetails.getVlt());
                return Optional.of(vagasVoluntariasRepository.save(VagasVoluntarias));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar VagasVoluntarias: " + e.getMessage());
        }
    }

    @Transactional
    public boolean delete(Long id) {
        try {
            Optional<VagasVoluntarias> existingVagasVoluntarias = vagasVoluntariasRepository.findById(id);
            if (existingVagasVoluntarias.isPresent()) {
                vagasVoluntariasRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar VagasVoluntarias: " + e.getMessage());
        }
    }
}
