package com.example.voluntariadointeligentehub.services;

import com.example.voluntariadointeligentehub.dto.RegistrarServicoRequest;
import com.example.voluntariadointeligentehub.dto.ServicoVoluntarioResponse;
import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.entities.ServicoVoluntario;
import com.example.voluntariadointeligentehub.entities.Voluntario;
import com.example.voluntariadointeligentehub.repositories.InstituicaoRepository;
import com.example.voluntariadointeligentehub.repositories.ServicoVoluntarioRepository;
import com.example.voluntariadointeligentehub.repositories.VoluntarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GestaoInstituicaoService {

    private static final Logger log = LoggerFactory.getLogger(GestaoInstituicaoService.class);

    private final ServicoVoluntarioRepository servicoRepo;
    private final InstituicaoRepository instituicaoRepo;
    private final VoluntarioRepository voluntarioRepo;

    // ==== injeção por construtor (sem Lombok) ====
    public GestaoInstituicaoService(ServicoVoluntarioRepository servicoRepo,
                                    InstituicaoRepository instituicaoRepo,
                                    VoluntarioRepository voluntarioRepo) {
        this.servicoRepo = servicoRepo;
        this.instituicaoRepo = instituicaoRepo;
        this.voluntarioRepo = voluntarioRepo;
    }

    @Transactional
    public ResponseEntity<ServicoVoluntarioResponse> registrarServico(RegistrarServicoRequest req) {
        log.info("[GestaoService] registrarServico inst={} vol={}", req.getInstituicaoId(), req.getVoluntarioId());

        Instituicao inst = instituicaoRepo.findById(req.getInstituicaoId())
                .orElseThrow(() -> {
                    log.warn("[GestaoService] instituicao {} não encontrada", req.getInstituicaoId());
                    return new RuntimeException("Instituição não encontrada");
                });

        Voluntario vol = voluntarioRepo.findById(req.getVoluntarioId())
                .orElseThrow(() -> {
                    log.warn("[GestaoService] voluntario {} não encontrado", req.getVoluntarioId());
                    return new RuntimeException("Voluntário não encontrado");
                });

        ServicoVoluntario s = new ServicoVoluntario();
        s.setInstituicao(inst);
        s.setVoluntario(vol);
        s.setDescricao(req.getDescricao());
        s.setHoras(req.getHoras());

        ServicoVoluntario saved = servicoRepo.save(s);
        log.info("[GestaoService] serviço registrado id={} inst={} vol={}", saved.getId(), inst.getId(), vol.getId());

        return ResponseEntity.ok(ServicoVoluntarioResponse.fromEntity(saved));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<ServicoVoluntarioResponse>> listarPorInstituicao(Long instituicaoId) {
        log.info("[GestaoService] listarPorInstituicao id={}", instituicaoId);
        var list = servicoRepo.findByInstituicao_IdOrderByCreatedAtDesc(instituicaoId)
                .stream().map(ServicoVoluntarioResponse::fromEntity).toList();
        return ResponseEntity.ok(list);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<ServicoVoluntarioResponse>> listarPorVoluntario(Long voluntarioId) {
        log.info("[GestaoService] listarPorVoluntario id={}", voluntarioId);
        var list = servicoRepo.findByVoluntario_IdOrderByCreatedAtDesc(voluntarioId)
                .stream().map(ServicoVoluntarioResponse::fromEntity).toList();
        return ResponseEntity.ok(list);
    }
}
