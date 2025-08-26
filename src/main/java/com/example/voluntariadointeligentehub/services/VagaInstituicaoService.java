package com.example.voluntariadointeligentehub.services;

import com.example.voluntariadointeligentehub.entities.VagaInstituicao;
import com.example.voluntariadointeligentehub.repositories.VagaInstituicaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VagaInstituicaoService {

    private static final Logger log = LoggerFactory.getLogger(VagaInstituicaoService.class);

    private final VagaInstituicaoRepository vagaRepo;

    public VagaInstituicaoService(VagaInstituicaoRepository vagaRepo) {
        this.vagaRepo = vagaRepo;
    }

    @Transactional
    public VagaInstituicao cadastrar(VagaInstituicao vaga) {
        System.out.println("[VagaInstituicaoService] cadastrar instituicaoId="
                + (vaga.getInstituicao() != null ? vaga.getInstituicao().getId() : null));
        VagaInstituicao saved = vagaRepo.save(vaga);
        log.info("Vaga cadastrada id={} instituicaoId={}",
                saved.getId(),
                saved.getInstituicao() != null ? saved.getInstituicao().getId() : null);
        System.out.println("[VagaInstituicaoService] cadastrar OK id=" + saved.getId());
        return saved;
    }

    @Transactional(readOnly = true)
    public List<VagaInstituicao> listar() {
        System.out.println("[VagaInstituicaoService] listar()");
        List<VagaInstituicao> list = vagaRepo.findAll();
        log.debug("listar -> {} registros", list.size());
        return list;
    }

    @Transactional(readOnly = true)
    public Optional<VagaInstituicao> buscarPorId(Long id) {
        System.out.println("[VagaInstituicaoService] buscarPorId id=" + id);
        Optional<VagaInstituicao> opt = vagaRepo.findById(id);
        log.debug("buscarPorId({}) -> presente={}", id, opt.isPresent());
        return opt;
    }

    @Transactional(readOnly = true)
    public List<VagaInstituicao> listarPorInstituicao(Long instituicaoId) {
        System.out.println("[VagaInstituicaoService] listarPorInstituicao instituicaoId=" + instituicaoId);
        List<VagaInstituicao> list = vagaRepo.findByInstituicaoId(instituicaoId);
        log.info("listarPorInstituicao({}) -> {} registros", instituicaoId, list.size());
        return list;
    }

    @Transactional(readOnly = true)
    public List<VagaInstituicao> listarComCandidatos() {
        System.out.println("[VagaInstituicaoService] listarComCandidatos()");
        List<VagaInstituicao> list = vagaRepo.findAllWithCandidaturas();
        log.info("listarComCandidatos -> {} registros (com fetch de candidaturas)", list.size());
        return list;
    }

    @Transactional(readOnly = true)
    public List<VagaInstituicao> listarComCandidatosPorInstituicao(Long instituicaoId) {
        System.out.println("[VagaInstituicaoService] listarComCandidatosPorInstituicao instituicaoId=" + instituicaoId);
        List<VagaInstituicao> list = vagaRepo.findByInstituicaoIdWithCandidaturas(instituicaoId);
        log.info("listarComCandidatosPorInstituicao({}) -> {} registros", instituicaoId, list.size());
        return list;
    }
}
