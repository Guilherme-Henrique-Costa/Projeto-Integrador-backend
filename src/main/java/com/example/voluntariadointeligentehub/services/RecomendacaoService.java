package com.example.voluntariadointeligentehub.services;

import com.example.voluntariadointeligentehub.dto.*;
import com.example.voluntariadointeligentehub.entities.Voluntario;
import com.example.voluntariadointeligentehub.entities.VagaInstituicao;
import com.example.voluntariadointeligentehub.repositories.VagaInstituicaoRepository;
import com.example.voluntariadointeligentehub.repositories.VoluntarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecomendacaoService {

    private final VoluntarioRepository voluntarioRepository;
    private final VagaInstituicaoRepository vagaInstituicaoRepository;

    public RecomendacaoService(VoluntarioRepository voluntarioRepository,
                               VagaInstituicaoRepository vagaInstituicaoRepository) {
        this.voluntarioRepository = voluntarioRepository;
        this.vagaInstituicaoRepository = vagaInstituicaoRepository;
    }

    @Transactional(readOnly = true)
    public RecomendacaoModelDTO gerarRecomendacoes(Long voluntarioId) {
        Optional<Voluntario> optVol = voluntarioRepository.findById(voluntarioId);
        if (optVol.isEmpty()) {
            throw new RuntimeException("Voluntário não encontrado");
        }

        Voluntario voluntario = optVol.get();

        // 🔹 Junta causas, habilidades e disponibilidade para matching
        String causas = String.join(",", Optional.ofNullable(voluntario.getCausas()).orElse(List.of())).toLowerCase();
        String habilidades = String.join(",", Optional.ofNullable(voluntario.getHabilidades()).orElse(List.of())).toLowerCase();
        String disponibilidade = String.join(",", Optional.ofNullable(voluntario.getDisponibilidadeSemanal()).orElse(List.of())).toLowerCase();

        // 🔹 Busca todas as vagas disponíveis
        List<VagaInstituicao> todasVagas = vagaInstituicaoRepository.findAll();

        // 🔹 Filtra as vagas recomendadas
        List<VagaInstituicao> vagasFiltradas = todasVagas.stream()
                .filter(v -> {
                    String cargo = Optional.ofNullable(v.getCargo()).orElse("").toLowerCase();
                    String descricao = Optional.ofNullable(v.getDescricao()).orElse("").toLowerCase();
                    String area = Optional.ofNullable(v.getArea()).orElse("").toLowerCase();
                    String tipo = Optional.ofNullable(v.getTipoVaga()).orElse("").toLowerCase();

                    return (causas.isEmpty() || cargo.contains(causas) || descricao.contains(causas) || area.contains(causas) || tipo.contains(causas))
                            || (habilidades.isEmpty() || cargo.contains(habilidades) || descricao.contains(habilidades) || area.contains(habilidades) || tipo.contains(habilidades))
                            || (disponibilidade.isEmpty() || (v.getDisponibilidade() != null && v.getDisponibilidade().toLowerCase().contains(disponibilidade)));
                })
                .limit(5)
                .collect(Collectors.toList());

        // 🔹 Caso não haja recomendadas, sorteia aleatórias
        if (vagasFiltradas.isEmpty()) {
            Collections.shuffle(todasVagas);
            vagasFiltradas = todasVagas.stream().limit(5).collect(Collectors.toList());
        }

        // 🔹 Converte vagas em DTO
        List<VagaRecomendadaDTO> vagasDTO = vagasFiltradas.stream()
                .map(v -> new VagaRecomendadaDTO(
                        v.getId(),
                        v.getCargo(),
                        v.getArea(),
                        v.getLocalidade()
                ))
                .collect(Collectors.toList());

        // 🔹 Mock das recompensas próximas (IA futuramente pode calcular isso)
        List<RecompensaProximaDTO> recompensas = List.of(
                new RecompensaProximaDTO("Voluntário Ouro", 80),
                new RecompensaProximaDTO("Colaborador Nota 10", 45)
        );

// 🔹 Mock das causas mais engajadas (IA futuramente calculará a partir do histórico)
        List<CausaEngajadaDTO> causasEngajadas = List.of(
                new CausaEngajadaDTO("Educação", 5),
                new CausaEngajadaDTO("Meio Ambiente", 3),
                new CausaEngajadaDTO("Saúde", 2)
        );

// 🔹 Retorna tudo junto para o Flutter
        return new RecomendacaoModelDTO(vagasDTO, recompensas, causasEngajadas);
    }
}
