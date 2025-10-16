package com.example.voluntariadointeligentehub.services;

import com.example.voluntariadointeligentehub.dto.VoluntarioEstatisticasDTO;
import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.entities.VagaInstituicao;
import com.example.voluntariadointeligentehub.entities.Voluntario;
import com.example.voluntariadointeligentehub.repositories.CandidaturaRepository;
import com.example.voluntariadointeligentehub.repositories.VagaInstituicaoRepository;
import com.example.voluntariadointeligentehub.repositories.VagasVoluntariasRepository;
import com.example.voluntariadointeligentehub.repositories.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class VoluntarioService {

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Autowired
    private VagaInstituicaoRepository vagaInstituicaoRepository;

    @Autowired
    private VagasVoluntariasRepository vagasVoluntariasRepository;

    @Autowired
    private VagaInstituicaoService vagaInstituicaoService;

    @Autowired
    private CandidaturaRepository candidaturaRepository;

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

    @Transactional(readOnly = true)
    public List<Map<String, Object>> getRecomendacoes(Long voluntarioId) {
        // 🔹 Busca o voluntário
        Voluntario voluntario = voluntarioRepository.findById(voluntarioId)
                .orElseThrow(() -> new RuntimeException("Voluntário não encontrado com ID: " + voluntarioId));

        // 🔹 Busca todas as vagas disponíveis
        List<VagaInstituicao> todasVagas = vagaInstituicaoRepository.findAll();

        // 🔹 Busca as vagas em que o voluntário já se candidatou
        List<Long> idsCandidatadas = vagasVoluntariasRepository.findByVoluntarioId(voluntarioId)
                .stream()
                .map(v -> v.getVaga().getId())
                .toList();

        // 🔹 Remove as vagas já candidatadas
        List<VagaInstituicao> vagasDisponiveis = todasVagas.stream()
                .filter(v -> !idsCandidatadas.contains(v.getId()))
                .toList();

        // 🔹 Junta listas de causas e habilidades em strings (evita erro de List.toLowerCase)
        String causas = voluntario.getCausas() != null
                ? String.join(",", voluntario.getCausas()).toLowerCase()
                : "";
        String habilidades = voluntario.getHabilidades() != null
                ? String.join(",", voluntario.getHabilidades()).toLowerCase()
                : "";
        String disponibilidade = voluntario.getDisponibilidadeSemanal() != null
                ? String.join(",", voluntario.getDisponibilidadeSemanal()).toLowerCase()
                : "";

        // 🔹 Faz o match com base nas palavras-chave
        List<VagaInstituicao> vagasCompatíveis = vagasDisponiveis.stream()
                .filter(v -> {
                    String cargo = v.getCargo() != null ? v.getCargo().toLowerCase() : "";
                    String descricao = v.getDescricao() != null ? v.getDescricao().toLowerCase() : "";
                    String area = v.getArea() != null ? v.getArea().toLowerCase() : "";
                    String tipo = v.getTipoVaga() != null ? v.getTipoVaga().toLowerCase() : "";

                    return (causas.isEmpty() || cargo.contains(causas) || descricao.contains(causas) || area.contains(causas) || tipo.contains(causas))
                            || (habilidades.isEmpty() || cargo.contains(habilidades) || descricao.contains(habilidades) || area.contains(habilidades) || tipo.contains(habilidades))
                            || (disponibilidade.isEmpty() || (v.getDisponibilidade() != null && v.getDisponibilidade().toLowerCase().contains(disponibilidade)));
                })
                .toList();

        // 🔹 Monta resposta leve para JSON
        List<Map<String, Object>> resposta = vagasCompatíveis.stream().map(v -> {
            Map<String, Object> vagaMap = new LinkedHashMap<>();
            vagaMap.put("id", v.getId());
            vagaMap.put("cargo", v.getCargo());
            vagaMap.put("descricao", v.getDescricao());
            vagaMap.put("localidade", v.getLocalidade());
            vagaMap.put("instituicao", v.getInstituicao() != null ? v.getInstituicao().getNome() : "Não informada");
            vagaMap.put("tipoVaga", v.getTipoVaga());
            vagaMap.put("area", v.getArea());
            vagaMap.put("disponibilidade", v.getDisponibilidade());
            vagaMap.put("status", "Recomendada");
            return vagaMap;
        }).toList();

        // 🔹 Caso não haja nenhuma vaga compatível
        if (resposta.isEmpty()) {
            Map<String, Object> msg = new HashMap<>();
            msg.put("mensagem", "Nenhuma vaga compatível encontrada. Atualize suas causas e habilidades para recomendações mais precisas.");
            resposta.add(msg);
        }

        return resposta;
    }

    @Transactional
    public boolean redefinirSenha(String email, String novaSenha) {
        System.out.println("[VoluntarioService] redefinirSenha email=" + email);

        // Busca o voluntário pelo e-mail institucional
        Optional<Voluntario> opt = voluntarioRepository.findByEmailInstitucional(email);

        if (opt.isEmpty()) {
            System.out.println("[VoluntarioService] Voluntário não encontrado.");
            return false;
        }

        Voluntario v = opt.get();
        v.setSenha(passwordEncoder.encode(novaSenha));
        voluntarioRepository.save(v);

        System.out.println("[VoluntarioService] Senha redefinida com sucesso!");
        return true;
    }

    @Transactional
    public VoluntarioEstatisticasDTO obterEstatisticas(Long id) {
        long candidaturas = candidaturaRepository.countByVoluntarioId(id);
        long concluidas = candidaturaRepository.countByVoluntarioIdAndStatus(id, "Concluída");
        List<Instituicao> instituicoes = candidaturaRepository.findDistinctInstituicoesByVoluntarioId(id);

        return new VoluntarioEstatisticasDTO(candidaturas, concluidas, instituicoes.size());
    }

}