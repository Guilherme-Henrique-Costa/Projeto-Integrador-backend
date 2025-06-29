package com.example.voluntariadointeligentehub.services;

import com.example.voluntariadointeligentehub.dto.ConversaDTO;
import com.example.voluntariadointeligentehub.entities.MensagemVoluntaria;
import com.example.voluntariadointeligentehub.repositories.MensagemVoluntariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MensagemVoluntariaService {

    @Autowired
    private MensagemVoluntariaRepository mensagemVoluntariaRepository;

    @Transactional
    public MensagemVoluntaria create(MensagemVoluntaria msg) {
        msg.setDataHora(LocalDateTime.now());
        return mensagemVoluntariaRepository.save(msg);
    }

    @Transactional(readOnly = true)
    public List<MensagemVoluntaria> findByVoluntarioId(Long id) {
        return mensagemVoluntariaRepository.findByVoluntario_IdOrderByDataHoraAsc(id);
    }

    @Transactional(readOnly = true)
    public List<ConversaDTO> gerarConversasPorInstituicao(Long voluntarioId) {
        List<MensagemVoluntaria> mensagens = findByVoluntarioId(voluntarioId);

        Map<String, MensagemVoluntaria> ultimaPorInstituicao = mensagens.stream()
                .collect(Collectors.toMap(
                        m -> m.getVoluntario().getInstituicao().getNome(),
                        m -> m,
                        (m1, m2) -> m1.getDataHora().isAfter(m2.getDataHora()) ? m1 : m2
                ));

        return ultimaPorInstituicao.values().stream()
                .map(m -> new ConversaDTO(
                        m.getVoluntario().getInstituicao().getNome(),
                        m.getMensagemVoluntario(),
                        m.getDataHora(),
                        true // ou lógica para determinar se foi lida
                ))
                .sorted(Comparator.comparing(ConversaDTO::getData).reversed())
                .collect(Collectors.toList());
    }
}
