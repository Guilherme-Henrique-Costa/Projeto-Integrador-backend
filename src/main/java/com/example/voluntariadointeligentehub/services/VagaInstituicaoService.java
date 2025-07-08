package com.example.voluntariadointeligentehub.services;

import com.example.voluntariadointeligentehub.entities.VagaInstituicao;
import com.example.voluntariadointeligentehub.repositories.VagaInstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VagaInstituicaoService {

    @Autowired
    private VagaInstituicaoRepository vagaRepo;

    public VagaInstituicao cadastrar(VagaInstituicao vaga) {
        return vagaRepo.save(vaga);
    }

    public List<VagaInstituicao> listar() {
        return vagaRepo.findAll();
    }

    public List<VagaInstituicao> listarComCandidatos() {
        List<VagaInstituicao> todas = vagaRepo.findAll();
        return todas.stream()
                .filter(v -> v.getCandidaturas() != null && !v.getCandidaturas().isEmpty())
                .collect(Collectors.toList());
    }

}

