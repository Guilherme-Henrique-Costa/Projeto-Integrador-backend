package com.example.voluntariadointeligentehub.controllers;

import com.example.voluntariadointeligentehub.dto.ConversaDTO;
import com.example.voluntariadointeligentehub.entities.MensagemVoluntaria;
import com.example.voluntariadointeligentehub.services.MensagemVoluntariaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/mensagem-voluntaria")
public class MensagemVoluntariaController {

    @Autowired
    private MensagemVoluntariaService mensagemVoluntariaService;

    @GetMapping("/voluntario/{id}/mensagens")
    public ResponseEntity<List<MensagemVoluntaria>> findByVoluntario(@PathVariable Long id) {
        List<MensagemVoluntaria> mensagens = mensagemVoluntariaService.findByVoluntarioId(id);
        return ResponseEntity.ok(mensagens);
    }

    @PostMapping
    public ResponseEntity<MensagemVoluntaria> create(@Valid @RequestBody MensagemVoluntaria voluntario) {
        MensagemVoluntaria created = mensagemVoluntariaService.create(voluntario);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/voluntario/{id}/conversas")
    public ResponseEntity<List<ConversaDTO>> getConversas(@PathVariable Long id) {
        List<ConversaDTO> conversas = mensagemVoluntariaService.gerarConversasPorInstituicao(id);
        return ResponseEntity.ok(conversas);
    }

}
