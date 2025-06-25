package com.example.voluntariadointeligentehub.services;

import com.example.voluntariadointeligentehub.entities.VagaInstituicao;
import com.example.voluntariadointeligentehub.repositories.VagaInstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}

