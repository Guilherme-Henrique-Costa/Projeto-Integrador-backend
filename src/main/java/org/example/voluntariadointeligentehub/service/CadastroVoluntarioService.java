package org.example.voluntariadointeligentehub.service;

import org.example.voluntariadointeligentehub.entity.Voluntario;
import org.example.voluntariadointeligentehub.repository.CadastroVoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;

@Service
public class CadastroVoluntarioService {

    @Autowired
    private CadastroVoluntarioRepository cadastroVoluntarioRepository;

    public Voluntario cadastrarVoluntario(Voluntario voluntario) {
        // Validação de idade mínima
        int idade = calcularIdade(voluntario.getDataNascimento());
        if (idade < 16) {
            throw new IllegalArgumentException("A idade mínima para cadastro é de 16 anos.");
        }

        // Validação de email duplicado
        if (cadastroVoluntarioRepository.findByEmail(voluntario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("O e-mail já está cadastrado.");
        }

        // Persistir o voluntário no banco de dados
        return cadastroVoluntarioRepository.save(voluntario);
    }

    // Método auxiliar para calcular a idade
    private int calcularIdade(LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
