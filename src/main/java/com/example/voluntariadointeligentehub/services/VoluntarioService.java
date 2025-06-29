package com.example.voluntariadointeligentehub.services;

import com.example.voluntariadointeligentehub.entities.Voluntario;
import com.example.voluntariadointeligentehub.repositories.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VoluntarioService {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Transactional
    public ResponseEntity<List<Voluntario>> findAll() {
        return ResponseEntity.ok(voluntarioRepository.findAll());
    }

    @Transactional
    public ResponseEntity<Optional<Voluntario>> findById(Long id) {
        return ResponseEntity.ok(voluntarioRepository.findById(id));
    }

    @Transactional
    public ResponseEntity<Optional<Voluntario>> findByNome(String nome) {
        return ResponseEntity.ok(voluntarioRepository.findByNome(nome));
    }

    @Transactional
    public ResponseEntity<Optional<Voluntario>> findByEmailInstitucional(String email) {
        return ResponseEntity.ok(voluntarioRepository.findByEmailInstitucional(email));
    }

    @Transactional
    public ResponseEntity<Boolean> verificarCpf(String cpf) {
        return ResponseEntity.ok(voluntarioRepository.findByCpf(cpf).isPresent());
    }

    @Transactional
    public Voluntario create(Voluntario voluntario) {
        voluntario.setSenha(encoder.encode(voluntario.getSenha()));
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
                    ? encoder.encode(data.getSenha())
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

    @Transactional
    public List<Voluntario> findByDisponibilidadeSemanal(String turno) {
        return voluntarioRepository.findByDisponibilidadeSemanalContaining(turno);
    }

    @Transactional
    public Optional<Voluntario> login(String email, String rawPassword) {
        return voluntarioRepository.findByEmailInstitucional(email)
                .filter(v -> encoder.matches(rawPassword, v.getSenha()));
    }
}
