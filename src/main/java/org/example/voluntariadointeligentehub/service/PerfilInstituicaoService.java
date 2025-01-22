//package org.example.voluntariadointeligentehub.service;
//
//import org.example.voluntariadointeligentehub.model.PerfilInstituicao;
//import org.example.voluntariadointeligentehub.repository.PerfilInstituicaoRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class PerfilInstituicaoService {
//
//    @Autowired
//    private PerfilInstituicaoRepository perfilInstituicaoRepository;
//
//    // Obter todos os perfis de instituição
//    public List<PerfilInstituicao> findAll() {
//        return perfilInstituicaoRepository.findAll();
//    }
//
//    // Obter perfil de instituição pelo ID
//    public PerfilInstituicao findById(Long id) {
//        Optional<PerfilInstituicao> perfil = perfilInstituicaoRepository.findById(id);
//        return perfil.orElse(null);
//    }
//
//    // Criar novo perfil
//    public PerfilInstituicao create(PerfilInstituicao perfilInstituicao) {
//        return perfilInstituicaoRepository.save(perfilInstituicao);
//    }
//
//    // Atualizar perfil existente
//    public PerfilInstituicao update(Long id, PerfilInstituicao perfilInstituicao) {
//        Optional<PerfilInstituicao> optionalPerfil = perfilInstituicaoRepository.findById(id);
//        if (optionalPerfil.isPresent()) {
//            PerfilInstituicao perfilExistente = optionalPerfil.get();
//            perfilExistente.setNome(perfilInstituicao.getNome());
//            perfilExistente.setEmail(perfilInstituicao.getEmail());
//            perfilExistente.setCnpj(perfilInstituicao.getCnpj());
//            perfilExistente.setPassword(perfilInstituicao.getPassword()); // Certifique-se de criptografar a senha antes de salvar
//            return perfilInstituicaoRepository.save(perfilExistente);
//        }
//        return null;
//    }
//
//    // Deletar perfil de instituição
//    public void delete(Long id) {
//        perfilInstituicaoRepository.deleteById(id);
//    }
//}
