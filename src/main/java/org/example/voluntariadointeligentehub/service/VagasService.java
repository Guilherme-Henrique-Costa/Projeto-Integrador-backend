package org.example.voluntariadointeligentehub.service;

import org.example.voluntariadointeligentehub.entity.Vagas;
import org.example.voluntariadointeligentehub.repository.CandidaturaRepository;
import org.example.voluntariadointeligentehub.repository.VagasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagasService {

    @Autowired
    private VagasRepository vagasRepository;

    @Autowired
    private CandidaturaRepository candidaturaRepository;

    public List<Vagas> listarVagasInstituicao() {
        return vagasRepository.findAll();
    }

    // Lista as vagas que possuem candidatos inscritos
    public List<Vagas> listarVagasComCandidatos() {
        List<Long> vagasComCandidatosIds = candidaturaRepository.findDistinctVagaIds();
        return vagasRepository.findAllById(vagasComCandidatosIds);
    }

    public Vagas salvarVagaInstituicao(Vagas vagas) {
        try {
            return vagasRepository.save(vagas);
        } catch (Exception e) {
            System.out.println("Erro ao salvar vaga: " + e.getMessage());
            e.printStackTrace();  // Exibir o stack trace completo para depuração
            throw e;
        }
    }
}
