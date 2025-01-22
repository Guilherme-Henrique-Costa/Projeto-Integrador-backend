package org.example.voluntariadointeligentehub.service;

import org.example.voluntariadointeligentehub.entity.PerfilInstituicao;
import org.example.voluntariadointeligentehub.repository.PerfilInstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroInstituicaoService {

    @Autowired
    private PerfilInstituicaoRepository perfilInstituicaoRepository;

    // Lógica de cadastro sem criptografia de senha
    public PerfilInstituicao cadastrarInstituicao(PerfilInstituicao perfilInstituicao) {
        // Verifica se o email já está cadastrado
        Optional<PerfilInstituicao> instituicaoExistente = perfilInstituicaoRepository.findByEmail(perfilInstituicao.getEmail());
        if (instituicaoExistente.isPresent()) {
            throw new IllegalArgumentException("O e-mail já está cadastrado.");
        }

        // Verifica se o CNPJ já está cadastrado
        instituicaoExistente = perfilInstituicaoRepository.findByCnpj(perfilInstituicao.getCnpj());
        if (instituicaoExistente.isPresent()) {
            throw new IllegalArgumentException("O CNPJ já está cadastrado.");
        }

        // Salva a instituição sem criptografar a senha
        return perfilInstituicaoRepository.save(perfilInstituicao);
    }
}
