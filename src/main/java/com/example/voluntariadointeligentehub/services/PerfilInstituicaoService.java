package com.example.voluntariadointeligentehub.services;

import java.util.List;
import java.util.Optional;

import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.entities.PerfilInstituicao;
import com.example.voluntariadointeligentehub.repositories.InstituicaoRepository;
import com.example.voluntariadointeligentehub.repositories.PerfilInstituicaoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PerfilInstituicaoService {

    private static final Logger log = LoggerFactory.getLogger(PerfilInstituicaoService.class);

    @Autowired private PerfilInstituicaoRepository perfilRepo;
    @Autowired private InstituicaoRepository instRepo;

    // ===== Leitura =====
    @Transactional(readOnly = true)
    public ResponseEntity<List<PerfilInstituicao>> findAll() {
        System.out.println("[PerfilInstituicaoService] findAll()");
        List<PerfilInstituicao> all = perfilRepo.findAll();
        log.debug("findAll -> {} registros", all.size());
        return ResponseEntity.ok(all);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Optional<PerfilInstituicao>> findById(Long id) {
        System.out.println("[PerfilInstituicaoService] findById id=" + id);
        Optional<PerfilInstituicao> opt = perfilRepo.findById(id);
        log.debug("findById({}) -> present={}", id, opt.isPresent());
        return ResponseEntity.ok(opt);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Optional<PerfilInstituicao>> findByInstituicaoId(Long instituicaoId) {
        System.out.println("[PerfilInstituicaoService] findByInstituicaoId instituicaoId=" + instituicaoId);
        Optional<PerfilInstituicao> opt = perfilRepo.findByInstituicaoId(instituicaoId);
        log.debug("findByInstituicaoId({}) -> present={}", instituicaoId, opt.isPresent());
        return ResponseEntity.ok(opt);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Optional<PerfilInstituicao>> findByEmail(String email) {
        System.out.println("[PerfilInstituicaoService] findByEmail email=" + email);
        Optional<PerfilInstituicao> opt = perfilRepo.findByInstituicaoEmail(email);
        log.debug("findByEmail('{}') -> present={}", email, opt.isPresent());
        return ResponseEntity.ok(opt);
    }

    // ===== Upsert por instituicaoId =====
    @Transactional
    public ResponseEntity<PerfilInstituicao> upsertByInstituicaoId(Long instituicaoId, PerfilInstituicao dados) {
        long start = System.currentTimeMillis();
        System.out.println("[PerfilInstituicaoService] upsert instituicaoId=" + instituicaoId);
        log.info("Upsert PerfilInstituicao instituicaoId={}", instituicaoId);

        Instituicao inst = instRepo.findById(instituicaoId)
                .orElseThrow(() -> new RuntimeException("Instituição não encontrada"));

        PerfilInstituicao perfil = perfilRepo.findByInstituicaoId(instituicaoId)
                .orElseGet(() -> {
                    PerfilInstituicao p = new PerfilInstituicao();
                    p.setInstituicao(inst);
                    log.debug("Criando novo PerfilInstituicao para instituicaoId={}", instituicaoId);
                    return p;
                });

        // aplica campos editáveis
        perfil.setLogoUrl(dados.getLogoUrl());
        perfil.setTelefone(dados.getTelefone());
        perfil.setWhatsapp(dados.getWhatsapp());
        perfil.setSite(dados.getSite());
        perfil.setCep(dados.getCep());
        perfil.setEndereco(dados.getEndereco());
        perfil.setNumero(dados.getNumero());
        perfil.setComplemento(dados.getComplemento());
        perfil.setBairro(dados.getBairro());
        perfil.setCidade(dados.getCidade());
        perfil.setUf(dados.getUf());

        PerfilInstituicao salvo = perfilRepo.save(perfil);
        long took = System.currentTimeMillis() - start;
        log.info("Upsert PerfilInstituicao OK instituicaoId={} perfilId={} ({}ms)",
                instituicaoId, salvo.getId(), took);
        System.out.println("[PerfilInstituicaoService] upsert OK perfilId=" + salvo.getId() + " (" + took + "ms)");
        return ResponseEntity.ok(salvo);
    }

    // ===== CRUD "puro" (se necessário) =====
    @Transactional
    public PerfilInstituicao create(PerfilInstituicao perfil) {
        System.out.println("[PerfilInstituicaoService] create instituicaoId="
                + (perfil.getInstituicao() != null ? perfil.getInstituicao().getId() : null));
        if (perfil.getInstituicao() == null || perfil.getInstituicao().getId() == null) {
            log.warn("create -> instituicao.id ausente");
            throw new RuntimeException("instituicao.id é obrigatório");
        }
        // garante existência
        instRepo.findById(perfil.getInstituicao().getId())
                .orElseThrow(() -> new RuntimeException("Instituição não encontrada"));

        PerfilInstituicao saved = perfilRepo.save(perfil);
        log.info("PerfilInstituicao criado id={} para instituicaoId={}",
                saved.getId(), perfil.getInstituicao().getId());
        System.out.println("[PerfilInstituicaoService] create OK perfilId=" + saved.getId());
        return saved;
    }

    @Transactional
    public Optional<PerfilInstituicao> update(Long id, PerfilInstituicao dados) {
        System.out.println("[PerfilInstituicaoService] update id=" + id);
        return perfilRepo.findById(id).map(p -> {
            p.setLogoUrl(dados.getLogoUrl());
            p.setTelefone(dados.getTelefone());
            p.setWhatsapp(dados.getWhatsapp());
            p.setSite(dados.getSite());
            p.setCep(dados.getCep());
            p.setEndereco(dados.getEndereco());
            p.setNumero(dados.getNumero());
            p.setComplemento(dados.getComplemento());
            p.setBairro(dados.getBairro());
            p.setCidade(dados.getCidade());
            p.setUf(dados.getUf());
            PerfilInstituicao saved = perfilRepo.save(p);
            log.info("PerfilInstituicao atualizado id={}", id);
            System.out.println("[PerfilInstituicaoService] update OK id=" + id);
            return saved;
        });
    }

    @Transactional
    public boolean delete(Long id) {
        System.out.println("[PerfilInstituicaoService] delete id=" + id);
        return perfilRepo.findById(id).map(p -> {
            perfilRepo.deleteById(id);
            log.info("PerfilInstituicao deletado id={}", id);
            System.out.println("[PerfilInstituicaoService] delete OK id=" + id);
            return true;
        }).orElseGet(() -> {
            log.warn("Tentativa de delete de PerfilInstituicao inexistente id={}", id);
            System.out.println("[PerfilInstituicaoService] delete NOT FOUND id=" + id);
            return false;
        });
    }
}
