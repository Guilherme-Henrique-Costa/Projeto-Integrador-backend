package com.example.voluntariadointeligentehub.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.repositories.InstituicaoRepository;

@Service
public class InstituicaoService {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    public ResponseEntity<List<Instituicao>> findAll() {
        try {
            List<Instituicao> resultado = instituicaoRepository.findAll();

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println("Erro ao buscar vagas voluntarias: " + e.getMessage());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar os Instituicaos.");
        }
    }

    @Transactional
    public ResponseEntity<Optional<Instituicao>> findById(Long id) {
        try {
            Optional<Instituicao> resultado = instituicaoRepository.findById(id);

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println(
                    "Erro ao buscar Instituicao por id: " + e.getMessage() + e.getCause());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar vagas voluntarias por id");
        }
    }

    @Transactional
    public ResponseEntity<Optional<Instituicao>> findByNome(String nome) {
        try {
            Optional<Instituicao> resultado = instituicaoRepository.findByNome(nome);

            return resultado.isPresent() ? ResponseEntity.ok(resultado) :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.err.println(
                    "Erro ao buscar instituicao por nome: " + e.getMessage() + e.getCause());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar instituicao por nome");
        }
    }

    @Transactional
    public ResponseEntity<Optional<Instituicao>> findByEmail(String email) {
        try {
            Optional<Instituicao> resultado = instituicaoRepository.findByEmail(email);

            return resultado.isPresent() ? ResponseEntity.ok(resultado) :
                    ResponseEntity.notFound().build();
        } catch (Exception e) {
            System.err.println(
                    "Erro ao buscar instituicao por email: " + e.getMessage() + e.getCause());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar instituicao por email");
        }
    }


    @Transactional
    public ResponseEntity<Optional<Instituicao>> findByPassword(String password) {
        try {
            Optional<Instituicao> resultado = instituicaoRepository.findByPassword(password);

            return ResponseEntity.ok(resultado);
        } catch (Exception e) {
            System.err.println(
                    "Erro ao buscar instituicao por senha: " + e.getMessage() + e.getCause());

            throw new RuntimeException(
                    "Ocorreu um erro ao buscar instituicao por senha");
        }
    }
    @Transactional
    public Instituicao create(Instituicao Instituicao) {
        try {
            return instituicaoRepository.save(Instituicao);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar Instituicao: " + e.getMessage());
        }
    }

    @Transactional
    public Optional<Instituicao> update(Long id, Instituicao instituicaoDetails) {
        try {
            Optional<Instituicao> existingInstituicao = instituicaoRepository.findById(id);
            if (existingInstituicao.isPresent()) {
                Instituicao instituicao = existingInstituicao.get();
                instituicao.setCnpj(instituicaoDetails.getCnpj());
                instituicao.setEmail(instituicaoDetails.getEmail());
                instituicao.setPassword(instituicaoDetails.getPassword());
                instituicao.setDescricao(instituicaoDetails.getDescricao());
                instituicao.setVgaAbrtInstru(instituicaoDetails.getVgaAbrtInstru());
                instituicao.setAvaliacao(instituicaoDetails.getAvaliacao());
                instituicao.setNome(instituicaoDetails.getNome());
                instituicao.setMensagemInstituicao(instituicaoDetails.getMensagemInstituicao());
                instituicao.setAreaInstituicao(instituicaoDetails.getAreaInstituicao());
                instituicao.setRltIntituicao(instituicaoDetails.getRltIntituicao());
                instituicao.setDescricaoInstituicao(instituicaoDetails.getDescricaoInstituicao());
                return Optional.of(instituicaoRepository.save(instituicao));
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
            Optional<Instituicao> existingInstituicao = instituicaoRepository.findById(id);
            if (existingInstituicao.isPresent()) {
                instituicaoRepository.deleteById(id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao deletar Instituicao: " + e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<String> register(String nome, String email, Integer cnpj, String areaInstituicao,  String password, String descricao) {
        try {
            Optional<Instituicao> existingInstituicao = instituicaoRepository.findByEmail(email);
            if (existingInstituicao.isPresent()) {
                return ResponseEntity.badRequest().body("E-mail já registrado.");
            }
            Instituicao instituicao = new Instituicao();
            instituicao.setNome(nome);
            instituicao.setEmail(email);
            instituicao.setCnpj(cnpj);
            instituicao.setAreaInstituicao(areaInstituicao);
            instituicao.setPassword(password);
            instituicao.setDescricao(descricao);

            instituicao.setPassword(passwordEncoder.encode(password));

            instituicaoRepository.save(instituicao);
            return ResponseEntity.ok("Usuário registrado com sucesso!");
        } catch (Exception e) {
            throw new RuntimeException("Erro ao registrar usuário: " + e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<String> login(String email, String password) {
        try {
            Optional<Instituicao> instituicaoOpt = instituicaoRepository.findByEmail(email);
            if (instituicaoOpt.isPresent()) {
                Instituicao instituicao = instituicaoOpt.get();

                if (passwordEncoder.matches(password, instituicao.getPassword())) {
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
