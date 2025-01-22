//package org.example.voluntariadointeligentehub.controller;
//
//import org.example.voluntariadointeligentehub.model.PerfilInstituicao;
//import org.example.voluntariadointeligentehub.service.PerfilInstituicaoService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/perfil-instituicao")
//public class PerfilInstituicaoController {
//
//    @Autowired
//    private PerfilInstituicaoService perfilInstituicaoService;
//
//    // Buscar todos os perfis de instituições
//    @GetMapping("/all")
//    public ResponseEntity<List<PerfilInstituicao>> findAll() {
//        List<PerfilInstituicao> perfis = perfilInstituicaoService.findAll();
//        return ResponseEntity.ok(perfis);
//    }
//
//    // Buscar perfil de instituição pelo ID
//    @GetMapping("/{id}")
//    public ResponseEntity<PerfilInstituicao> findById(@PathVariable Long id) {
//        PerfilInstituicao perfil = perfilInstituicaoService.findById(id);
//        return perfil != null ? ResponseEntity.ok(perfil) : ResponseEntity.notFound().build();
//    }
//
//    // Criar novo perfil de instituição
//    @PostMapping
//    public ResponseEntity<PerfilInstituicao> create(@RequestBody PerfilInstituicao perfilInstituicao) {
//        PerfilInstituicao novoPerfil = perfilInstituicaoService.create(perfilInstituicao);
//        return ResponseEntity.ok(novoPerfil);
//    }
//
//    // Atualizar perfil de instituição existente
//    @PutMapping("/{id}")
//    public ResponseEntity<PerfilInstituicao> update(@PathVariable Long id, @RequestBody PerfilInstituicao perfilInstituicao) {
//        PerfilInstituicao perfilAtualizado = perfilInstituicaoService.update(id, perfilInstituicao);
//        return perfilAtualizado != null ? ResponseEntity.ok(perfilAtualizado) : ResponseEntity.notFound().build();
//    }
//
//    // Deletar perfil de instituição
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        perfilInstituicaoService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//}
