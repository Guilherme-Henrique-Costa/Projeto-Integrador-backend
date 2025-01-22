package org.example.voluntariadointeligentehub.controller;

import org.example.voluntariadointeligentehub.entity.Vagas;
import org.example.voluntariadointeligentehub.service.VagasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vagasInstituicao")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class VagasController {

    @Autowired
    private VagasService vagasService;

    // Endpoint para listar todas as vagas da instituição
    @GetMapping
    public List<Vagas> listarVagasInstituicao() {
        return vagasService.listarVagasInstituicao();
    }

    // Endpoint para listar vagas com candidatos
    @GetMapping("/com-candidatos")
    public List<Vagas> listarVagasComCandidatos() {
        return vagasService.listarVagasComCandidatos();
    }
    // Endpoint para salvar uma nova vaga da instituição
    @PostMapping
    public ResponseEntity<Vagas> criarVagaInstituicao(@RequestBody Vagas vagas) {
        System.out.println("Recebendo vaga: " + vagas);  // Verificar se o objeto está correto
        System.out.println("Especificações recebidas: " + vagas.getEspecificacoes());  // Verificar o conteúdo da lista

        if (vagas.getEspecificacoes() == null || vagas.getEspecificacoes().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);  // Retornar erro se o campo estiver vazio
        }

        Vagas novaVaga = vagasService.salvarVagaInstituicao(vagas);
        return new ResponseEntity<>(novaVaga, HttpStatus.CREATED);
    }


}
