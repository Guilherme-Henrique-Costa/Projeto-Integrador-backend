package com.example.voluntariadointeligentehub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.voluntariadointeligentehub.entities.FeedbackVoluntario;
import com.example.voluntariadointeligentehub.repositories.FeedbackVoluntarioRepository;

@Service
public class FeedbackVoluntarioService {

    @Autowired
    private FeedbackVoluntarioRepository feedbackVoluntarioRepository;

    @Transactional
    public ResponseEntity<List<FeedbackVoluntario>> findAll() {
        try {
            List<FeedbackVoluntario> resultado = feedbackVoluntarioRepository.findAll();

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println("Erro ao buscar vagas voluntarias: " + e.getMessage());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar os FeedbackVoluntarios.");
        }
    }

    @Transactional
    public ResponseEntity<Optional<FeedbackVoluntario>> findById(Long id) {
        try {
            Optional<FeedbackVoluntario> resultado = feedbackVoluntarioRepository.findById(id);

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println(
                    "Erro ao buscar FeedbackVoluntario por id: " + e.getMessage() + e.getCause());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar vagas voluntarias por id");
        }
    }

    // @Transactional
    // public ResponseEntity<Optional<FeedbackVoluntario>> findByDescricaoVaga(String descricaoVaga) {
    //     try {
    //         Optional<FeedbackVoluntario> resultado = feedbackVoluntarioRepository.findByDescricaoVaga(descricaoVaga);
    //         return resultado.isPresent() ? ResponseEntity.ok(resultado) :
    //                 ResponseEntity.notFound().build();
    //     } catch (Exception e) {
    //         System.err.println(
    //                 "Erro ao buscar FeedbackVoluntario por nome: " + e.getMessage() + e.getCause());
    //         throw new RuntimeException(
    //                 "Ocorreu um erro ao buscar FeedbackVoluntario por nome");
    //     }
    // }
    // @Transactional
    // public ResponseEntity<Optional<FeedbackVoluntario>> findByArea(String area) {
    //     try {
    //         Optional<FeedbackVoluntario> resultado = feedbackVoluntarioRepository.findByArea(area);
    //         return resultado.isPresent() ? ResponseEntity.ok(resultado) :
    //                 ResponseEntity.notFound().build();
    //     } catch (Exception e) {
    //         System.err.println(
    //                 "Erro ao buscar FeedbackVoluntario por email: " + e.getMessage() + e.getCause());
    //         throw new RuntimeException(
    //                 "Ocorreu um erro ao buscar FeedbackVoluntario por email");
    //     }
    // }
    // @Transactional
    // public ResponseEntity<Optional<FeedbackVoluntario>> findByVagaAbrt(String vagaAbrt) {
    //     try {
    //         Optional<FeedbackVoluntario> resultado = feedbackVoluntarioRepository.findByVagaAbrt(vagaAbrt);
    //         return ResponseEntity.ok(resultado);
    //     } catch (Exception e) {
    //         System.err.println(
    //                 "Erro ao buscar FeedbackVoluntario por senha: " + e.getMessage() + e.getCause());
    //         throw new RuntimeException(
    //                 "Ocorreu um erro ao buscar FeedbackVoluntario por senha");
    //     }
    // }
    @Transactional
    public FeedbackVoluntario create(FeedbackVoluntario FeedbackVoluntario) {
        try {
            return feedbackVoluntarioRepository.save(FeedbackVoluntario);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar FeedbackVoluntario: " + e.getMessage());
        }
    }

    @Transactional
    public Optional<FeedbackVoluntario> update(Long id, FeedbackVoluntario feedbackVoluntarioDetails) {
        try {
            Optional<FeedbackVoluntario> existingFeedbackVoluntario = feedbackVoluntarioRepository.findById(id);
            if (existingFeedbackVoluntario.isPresent()) {
                FeedbackVoluntario feedbackVoluntario = existingFeedbackVoluntario.get();
                feedbackVoluntario.setDescricaoVaga(feedbackVoluntarioDetails.getDescricaoVaga());
                feedbackVoluntario.setFeedback(feedbackVoluntarioDetails.getFeedback());
                return Optional.of(feedbackVoluntarioRepository.save(feedbackVoluntario));
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar Perfil FeedbackVoluntario: " + e.getMessage());
        }
    }

    @Transactional
    public boolean delete(Long id) {
        try {
            Optional<FeedbackVoluntario> existingMensagemVoluntaria = feedbackVoluntarioRepository.findById(id);
            if (existingMensagemVoluntaria.isPresent()) {
                feedbackVoluntarioRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar MensagemVoluntaria: " + e.getMessage());
        }
    }
}