package com.example.voluntariadointeligentehub.controllers;

import com.example.voluntariadointeligentehub.dto.VagasVoluntariasDTO;
import com.example.voluntariadointeligentehub.repositories.VagasVoluntariasRepository;
import com.example.voluntariadointeligentehub.services.VagasVoluntariasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
public class VagasVoluntariasController {

    private static final Logger log = LoggerFactory.getLogger(VagasVoluntariasController.class);

    @Autowired
    private VagasVoluntariasService service;

    @Autowired
    private VagasVoluntariasRepository candidaturaRepository;

    // GET /api/v1/vagasCandidatadas/{voluntarioId}
    @GetMapping("/vagasCandidatadas/{voluntarioId}")
    public ResponseEntity<List<VagasVoluntariasDTO>> buscarCandidatadas(@PathVariable Long voluntarioId) {
        long start = System.currentTimeMillis();
        System.out.println("[VagasVoluntariasController] GET /vagasCandidatadas/" + voluntarioId);
        log.info("Listar vagas candidatadas por voluntarioId={}", voluntarioId);

        List<VagasVoluntariasDTO> dtos = candidaturaRepository.findByVoluntarioId(voluntarioId)
                .stream()
                .map(VagasVoluntariasDTO::fromEntity)
                .collect(Collectors.toList());

        log.info("vagasCandidatadas -> {} itens ({}ms)", dtos.size(), (System.currentTimeMillis() - start));
        return ResponseEntity.ok(dtos);
    }
}
