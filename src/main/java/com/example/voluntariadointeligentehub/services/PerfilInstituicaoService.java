package com.example.voluntariadointeligentehub.services;

import java.util.List;
import java.util.Optional;

import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.entities.PerfilInstituicao;
import com.example.voluntariadointeligentehub.repositories.InstituicaoRepository;
import com.example.voluntariadointeligentehub.repositories.PerfilInstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilInstituicaoService {

    @Autowired
    private PerfilInstituicaoRepository perfilInstituicaoRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;


    @Transactional
    public ResponseEntity<List<PerfilInstituicao>> findAll() {
        try {
            List<PerfilInstituicao> resultado = perfilInstituicaoRepository.findAll();
            System.out.println("📋 Total de perfis de instituições encontrados: " + resultado.size());
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar perfis: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar perfis");
        }
    }

    @Transactional
    public ResponseEntity<Optional<PerfilInstituicao>> findById(Long id) {
        try {
            Optional<PerfilInstituicao> resultado = perfilInstituicaoRepository.findById(id);
            System.out.println("🔍 Resultado por ID: " + resultado);
            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println("❌ Erro ao buscar por ID: " + e.getMessage());
            throw new RuntimeException("Erro ao buscar por ID");
        }
    }

    @Transactional
    public PerfilInstituicao create(PerfilInstituicao perfilInstituicao) {
        try {
            PerfilInstituicao criado = perfilInstituicaoRepository.save(perfilInstituicao);
            System.out.println("✅ Perfil criado: " + criado.getNome());
            return criado;
        } catch (Exception e) {
            System.err.println("❌ Erro ao criar perfil: " + e.getMessage());
            throw new RuntimeException("Erro ao criar perfil");
        }
    }

    @Transactional
    public Optional<PerfilInstituicao> update(Long id, PerfilInstituicao dados) {
        try {
            Optional<PerfilInstituicao> existente = perfilInstituicaoRepository.findById(id);
            if (existente.isPresent()) {
                PerfilInstituicao atual = existente.get();
                atual.setCnpj(dados.getCnpj());
                atual.setEmail(dados.getEmail());
                atual.setPassword(dados.getPassword());
                atual.setNome(dados.getNome());

                PerfilInstituicao salvo = perfilInstituicaoRepository.save(atual);
                System.out.println("✏️ Perfil atualizado: " + salvo.getNome());
                return Optional.of(salvo);
            } else {
                System.out.println("⚠️ Perfil com ID " + id + " não encontrado.");
                return Optional.empty();
            }
        } catch (Exception e) {
            System.err.println("❌ Erro ao atualizar: " + e.getMessage());
            throw new RuntimeException("Erro ao atualizar");
        }
    }

    @Transactional
    public boolean delete(Long id) {
        try {
            Optional<PerfilInstituicao> existente = perfilInstituicaoRepository.findById(id);
            if (existente.isPresent()) {
                perfilInstituicaoRepository.deleteById(id);
                System.out.println("🗑️ Perfil deletado: " + id);
                return true;
            } else {
                System.out.println("⚠️ Perfil com ID " + id + " não encontrado para deletar.");
                return false;
            }
        } catch (Exception e) {
            System.err.println("❌ Erro ao deletar: " + e.getMessage());
            throw new RuntimeException("Erro ao deletar");
        }
    }

    @Transactional
    public ResponseEntity<Optional<Instituicao>> findByEmail(String email) {
        try {
            System.out.println("🔍 Buscando instituição por e-mail: " + email);
            Optional<Instituicao> resultado = instituicaoRepository.findByEmail(email);
            if (resultado.isPresent()) {
                System.out.println("✅ Instituição encontrada: " + resultado.get().getNome());
                return ResponseEntity.ok(resultado);
            } else {
                System.out.println("⚠️ Instituição não encontrada.");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar instituição por e-mail: " + e.getMessage());
        }
    }

}
