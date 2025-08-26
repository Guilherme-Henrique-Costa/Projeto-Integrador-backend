package com.example.voluntariadointeligentehub.services;

import com.example.voluntariadointeligentehub.entities.Voluntario;
import com.example.voluntariadointeligentehub.repositories.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class VoluntarioService {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public ResponseEntity<List<Voluntario>> findAll() {
        return ResponseEntity.ok(voluntarioRepository.findAll());
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Optional<Voluntario>> findById(Long id) {
        return ResponseEntity.ok(voluntarioRepository.findById(id));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Optional<Voluntario>> findByNome(String nome) {
        return ResponseEntity.ok(voluntarioRepository.findByNome(nome));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Optional<Voluntario>> findByEmailInstitucional(String email) {
        return ResponseEntity.ok(voluntarioRepository.findByEmailInstitucional(email));
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Boolean> verificarCpf(String cpf) {
        return ResponseEntity.ok(voluntarioRepository.findByCpf(cpf).isPresent());
    }

    @Transactional
    public Voluntario create(Voluntario voluntario) {
        voluntario.setSenha(passwordEncoder.encode(voluntario.getSenha()));
        return voluntarioRepository.save(voluntario);
    }

    @Transactional
    public Optional<Voluntario> update(Long id, Voluntario data) {
        return voluntarioRepository.findById(id).map(v -> {
            v.setMatricula(data.getMatricula());
            v.setNome(data.getNome());
            v.setCpf(data.getCpf());
            v.setDataNascimento(data.getDataNascimento());
            v.setGenero(data.getGenero());
            v.setSenha(data.getSenha() != null && !data.getSenha().isBlank()
                    ? passwordEncoder.encode(data.getSenha())
                    : v.getSenha());
            v.setAtividadeCEUB(data.getAtividadeCEUB());
            v.setEmailInstitucional(data.getEmailInstitucional());
            v.setEmailParticular(data.getEmailParticular());
            v.setCelular(data.getCelular());
            v.setCidadeUF(data.getCidadeUF());
            v.setHorario(data.getHorario());
            v.setMotivacao(data.getMotivacao());
            v.setCausas(data.getCausas());
            v.setHabilidades(data.getHabilidades());
            v.setDisponibilidadeSemanal(data.getDisponibilidadeSemanal());
            v.setComentarios(data.getComentarios());
            v.setAvatarPath(data.getAvatarPath());
            v.setInstituicao(data.getInstituicao());
            return voluntarioRepository.save(v);
        });
    }

    @Transactional
    public boolean delete(Long id) {
        return voluntarioRepository.findById(id).map(v -> {
            voluntarioRepository.deleteById(id);
            return true;
        }).orElse(false);
    }

    @Transactional(readOnly = true)
    public List<Voluntario> findByDisponibilidadeSemanal(String turno) {
        return voluntarioRepository.findByDisponibilidadeSemanalContaining(turno);
    }

    @Transactional(readOnly = true)
    public Optional<Voluntario> login(String email, String rawPassword) {
        return voluntarioRepository.findByEmailInstitucional(email)
                .filter(v -> passwordEncoder.matches(rawPassword, v.getSenha()));
    }

    @Transactional(readOnly = true)
    public List<Voluntario> search(String q) {
        return voluntarioRepository.search((q == null || q.isBlank()) ? null : q.trim());
    }

    @Transactional(readOnly = true)
    public Voluntario buscarPorEmail(String email) {
        return voluntarioRepository.findByEmailInstitucional(email)
                .orElseThrow(() -> new RuntimeException("Voluntário não encontrado"));
    }
}