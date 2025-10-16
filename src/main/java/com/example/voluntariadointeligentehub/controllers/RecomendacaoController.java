package com.example.voluntariadointeligentehub.controllers;

import com.example.voluntariadointeligentehub.dto.RecomendacaoDTO;
import com.example.voluntariadointeligentehub.dto.RecomendacaoModelDTO;
import com.example.voluntariadointeligentehub.services.RecomendacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/recomendacoes")
@CrossOrigin(origins = "*")
public class RecomendacaoController {

    @Autowired
    private RecomendacaoService recomendacaoService;

    @GetMapping("/{id}/recomendacoes")
    public ResponseEntity<RecomendacaoModelDTO> gerarRecomendacoes(@PathVariable Long id) {
        RecomendacaoModelDTO recomendacoes = recomendacaoService.gerarRecomendacoes(id);
        return ResponseEntity.ok(recomendacoes);
    }
}
