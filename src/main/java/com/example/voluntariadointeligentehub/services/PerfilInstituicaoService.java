package com.example.voluntariadointeligentehub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.voluntariadointeligentehub.entities.PerfilInstituicao;
import com.example.voluntariadointeligentehub.repositories.PerfilInstituicaoRepository;

@Service
public class PerfilInstituicaoService {
    
    @Autowired
    private PerfilInstituicaoRepository perfilInstituicaoRepository;

    @Transactional
    public ResponseEntity<List<PerfilInstituicao>> findAll() {
        try {
            List<PerfilInstituicao> resultado = perfilInstituicaoRepository.findAll();

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println("Erro ao buscar vagas voluntarias: " + e.getMessage());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar os PerfilInstituicaos.");
        }
    }

    @Transactional
    public ResponseEntity<Optional<PerfilInstituicao>> findById(Long id) {
        try {
            Optional<PerfilInstituicao> resultado = perfilInstituicaoRepository.findById(id);

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println(
                    "Erro ao buscar PerfilInstituicao por id: " + e.getMessage() + e.getCause());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar vagas voluntarias por id");
        }
    }

    // @Transactional
    // public ResponseEntity<Optional<PerfilInstituicao>> findByDescricaoVaga(String descricaoVaga) {
    //     try {
    //         Optional<PerfilInstituicao> resultado = perfilInstituicaoRepository.findByDescricaoVaga(descricaoVaga);

    //         return resultado.isPresent() ? ResponseEntity.ok(resultado) :
    //                 ResponseEntity.notFound().build();
    //     } catch (Exception e) {
    //         System.err.println(
    //                 "Erro ao buscar PerfilInstituicao por nome: " + e.getMessage() + e.getCause());

    //         throw new RuntimeException(
    //                 "Ocorreu um erro ao buscar PerfilInstituicao por nome");
    //     }
    // }

    // @Transactional
    // public ResponseEntity<Optional<PerfilInstituicao>> findByArea(String area) {
    //     try {
    //         Optional<PerfilInstituicao> resultado = perfilInstituicaoRepository.findByArea(area);

    //         return resultado.isPresent() ? ResponseEntity.ok(resultado) :
    //                 ResponseEntity.notFound().build();
    //     } catch (Exception e) {
    //         System.err.println(
    //                 "Erro ao buscar PerfilInstituicao por email: " + e.getMessage() + e.getCause());

    //         throw new RuntimeException(
    //                 "Ocorreu um erro ao buscar PerfilInstituicao por email");
    //     }
    // }


    // @Transactional
    // public ResponseEntity<Optional<PerfilInstituicao>> findByVagaAbrt(String vagaAbrt) {
    //     try {
    //         Optional<PerfilInstituicao> resultado = perfilInstituicaoRepository.findByVagaAbrt(vagaAbrt);

    //         return ResponseEntity.ok(resultado);
    //     } catch (Exception e) {
    //         System.err.println(
    //                 "Erro ao buscar PerfilInstituicao por senha: " + e.getMessage() + e.getCause());

    //         throw new RuntimeException(
    //                 "Ocorreu um erro ao buscar PerfilInstituicao por senha");
    //     }
    // }

    @Transactional
    public PerfilInstituicao create(PerfilInstituicao PerfilInstituicao) {
        try {
            return perfilInstituicaoRepository.save(PerfilInstituicao);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar PerfilInstituicao: " + e.getMessage());
        }
    }

    @Transactional
public Optional<PerfilInstituicao> update(Long id, PerfilInstituicao perfilInstituicaoDetails) {
    try {
        Optional<PerfilInstituicao> existingPerfilInstituicao = perfilInstituicaoRepository.findById(id);
        if (existingPerfilInstituicao.isPresent()) {
            PerfilInstituicao perfilInstituicao = existingPerfilInstituicao.get();
            perfilInstituicao.setCnpj(perfilInstituicaoDetails.getCnpj());
            perfilInstituicao.setEmail(perfilInstituicaoDetails.getEmail());
            perfilInstituicao.setPassword(perfilInstituicaoDetails.getPassword());
            perfilInstituicao.setNome(perfilInstituicaoDetails.getNome());
            return Optional.of(perfilInstituicaoRepository.save(perfilInstituicao));
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
            Optional<PerfilInstituicao> existingPerfilInstituicao = perfilInstituicaoRepository.findById(id);
            if (existingPerfilInstituicao.isPresent()) {
                perfilInstituicaoRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar PerfilInstituicao: " + e.getMessage());
        }
    }
}
