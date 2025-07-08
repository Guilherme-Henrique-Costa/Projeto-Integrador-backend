package com.example.voluntariadointeligentehub.controllers;

import com.example.voluntariadointeligentehub.entities.VagaInstituicao;
import com.example.voluntariadointeligentehub.services.VagaInstituicaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class VagasInstituicaoController {

    @Autowired
    private VagaInstituicaoService vagaService;

    @PostMapping("/vagasInstituicao")
    public ResponseEntity<VagaInstituicao> cadastrar(@RequestBody VagaInstituicao vaga) {
        VagaInstituicao salva = vagaService.cadastrar(vaga);
        return ResponseEntity.ok(salva);
    }

    @GetMapping("/vagasInstituicao")
    public ResponseEntity<List<VagaInstituicao>> listar() {
        return ResponseEntity.ok(vagaService.listar());
    }

    @GetMapping("/vagasInstituicao/com-candidatos")
    public ResponseEntity<List<VagaInstituicao>> listarVagasComCandidatos() {
        List<VagaInstituicao> vagas = vagaService.listarComCandidatos();
        return ResponseEntity.ok(vagas);
    }

}
