package com.example.voluntariadointeligentehub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.voluntariadointeligentehub.entities.MensagemVoluntaria;
import com.example.voluntariadointeligentehub.repositories.MensagemVoluntariaRepository;

@Service
public class MensagemVoluntariaService {

    @Autowired
    private MensagemVoluntariaRepository mensagemVoluntariaRepository;

    @Transactional
    public ResponseEntity<List<MensagemVoluntaria>> findAll() {
        try {
            List<MensagemVoluntaria> resultado = mensagemVoluntariaRepository.findAll();

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println("Erro ao buscar vagas voluntarias: " + e.getMessage());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar os MensagemVoluntarias.");
        }
    }

    @Transactional
    public ResponseEntity<Optional<MensagemVoluntaria>> findById(Long id) {
        try {
            Optional<MensagemVoluntaria> resultado = mensagemVoluntariaRepository.findById(id);

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println(
                    "Erro ao buscar MensagemVoluntaria por id: " + e.getMessage() + e.getCause());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar vagas voluntarias por id");
        }
    }

    // @Transactional
    // public ResponseEntity<Optional<MensagemVoluntaria>> findByDescricaoVaga(String descricaoVaga) {
    //     try {
    //         Optional<MensagemVoluntaria> resultado = mensagemVoluntariaRepository.findByDescricaoVaga(descricaoVaga);
    //         return resultado.isPresent() ? ResponseEntity.ok(resultado) :
    //                 ResponseEntity.notFound().build();
    //     } catch (Exception e) {
    //         System.err.println(
    //                 "Erro ao buscar MensagemVoluntaria por nome: " + e.getMessage() + e.getCause());
    //         throw new RuntimeException(
    //                 "Ocorreu um erro ao buscar MensagemVoluntaria por nome");
    //     }
    // }
    // @Transactional
    // public ResponseEntity<Optional<MensagemVoluntaria>> findByArea(String area) {
    //     try {
    //         Optional<MensagemVoluntaria> resultado = mensagemVoluntariaRepository.findByArea(area);
    //         return resultado.isPresent() ? ResponseEntity.ok(resultado) :
    //                 ResponseEntity.notFound().build();
    //     } catch (Exception e) {
    //         System.err.println(
    //                 "Erro ao buscar MensagemVoluntaria por email: " + e.getMessage() + e.getCause());
    //         throw new RuntimeException(
    //                 "Ocorreu um erro ao buscar MensagemVoluntaria por email");
    //     }
    // }
    // @Transactional
    // public ResponseEntity<Optional<MensagemVoluntaria>> findByVagaAbrt(String vagaAbrt) {
    //     try {
    //         Optional<MensagemVoluntaria> resultado = mensagemVoluntariaRepository.findByVagaAbrt(vagaAbrt);
    //         return ResponseEntity.ok(resultado);
    //     } catch (Exception e) {
    //         System.err.println(
    //                 "Erro ao buscar MensagemVoluntaria por senha: " + e.getMessage() + e.getCause());
    //         throw new RuntimeException(
    //                 "Ocorreu um erro ao buscar MensagemVoluntaria por senha");
    //     }
    // }
    @Transactional
    public MensagemVoluntaria create(MensagemVoluntaria MensagemVoluntaria) {
        try {
            return mensagemVoluntariaRepository.save(MensagemVoluntaria);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar MensagemVoluntaria: " + e.getMessage());
        }
    }

    @Transactional
    public Optional<MensagemVoluntaria> update(Long id, MensagemVoluntaria mensagemVoluntariaDetails) {
        try {
            Optional<MensagemVoluntaria> existingMensagemVoluntaria = mensagemVoluntariaRepository.findById(id);
            if (existingMensagemVoluntaria.isPresent()) {
                MensagemVoluntaria mensagemVoluntaria = existingMensagemVoluntaria.get();
                mensagemVoluntaria.setVoluntarioNome(mensagemVoluntariaDetails.getVoluntarioNome());
                mensagemVoluntaria.setMensagemVoluntario(mensagemVoluntariaDetails.getMensagemVoluntario());
                return Optional.of(mensagemVoluntariaRepository.save(mensagemVoluntaria));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar Perfil Instituicao: " + e.getMessage());
        }
    }

    @Transactional
    public boolean delete(Long id) {
        try {
            Optional<MensagemVoluntaria> existingMensagemVoluntaria = mensagemVoluntariaRepository.findById(id);
            if (existingMensagemVoluntaria.isPresent()) {
                mensagemVoluntariaRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar MensagemVoluntaria: " + e.getMessage());
        }
    }
}
