package org.example.voluntariadointeligentehub.service;

import org.example.voluntariadointeligentehub.entity.PerfilInstituicao;
import org.example.voluntariadointeligentehub.repository.PerfilInstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginInstituicaoService {

    @Autowired
    private PerfilInstituicaoRepository perfilInstituicaoRepository;

    // Autenticar a instituição
    public PerfilInstituicao autenticar(String email, String password) {
        // Busca a instituição pelo email
        PerfilInstituicao instituicao = perfilInstituicaoRepository.findByEmail(email).orElse(null);

        if (instituicao != null) {
            // Verifica se a senha corresponde diretamente
            if (instituicao.getPassword().equals(password)) {
                // Se a senha for válida, retorna o perfil da instituição, sem modificar a senha
                return instituicao;
            }
        }

        // Se não encontrou o usuário ou a senha não corresponde, retorna null
        return null;
    }
}
