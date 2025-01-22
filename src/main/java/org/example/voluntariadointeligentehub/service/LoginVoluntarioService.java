package org.example.voluntariadointeligentehub.service;

import org.example.voluntariadointeligentehub.entity.Voluntario;
import org.example.voluntariadointeligentehub.repository.CadastroVoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginVoluntarioService {

    @Autowired
    private CadastroVoluntarioRepository cadastroVoluntarioRepository;

    // Lógica de login para o voluntário
    public Voluntario loginVoluntario(String email, String password) {
        // Busca o voluntário pelo email
        Optional<Voluntario> voluntarioOpt = cadastroVoluntarioRepository.findByEmail(email);

        // Verifica se o voluntário foi encontrado
        if (voluntarioOpt.isPresent()) {
            Voluntario voluntario = voluntarioOpt.get();

            // Verifica se o password e voluntario.getSenha() não são nulos e se coincidem
            if (password != null && voluntario.getSenha() != null && password.equals(voluntario.getSenha())) {
                return voluntario;  // Se a senha coincidir, retorna o voluntário
            } else {
                throw new IllegalArgumentException("Senha incorreta ou não informada.");
            }
        }

        // Se não encontrou o usuário, lança exceção
        throw new IllegalArgumentException("Voluntário não encontrado com o email fornecido.");
    }
}
