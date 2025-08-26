package com.example.voluntariadointeligentehub.services;

import java.util.*;

import com.example.voluntariadointeligentehub.entities.Voluntario;
import com.example.voluntariadointeligentehub.entities.Instituicao;
import com.example.voluntariadointeligentehub.jwt.JwtUtil;
import com.example.voluntariadointeligentehub.repositories.InstituicaoRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InstituicaoService {

    private static final Logger log = LoggerFactory.getLogger(InstituicaoService.class);

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    @Autowired
    private JwtUtil jwtUtil;

    // usa o bean declarado no SecurityConfig
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    private final long jwtExpirationMs = 86400000; // 24h (se precisar usar em JwtUtil)

    @Transactional
    public ResponseEntity<List<Instituicao>> findAll() {
        System.out.println("[InstituicaoService] findAll()");
        List<Instituicao> list = instituicaoRepository.findAll();
        log.debug("findAll -> {} registros", list.size());
        return ResponseEntity.ok(list);
    }

    @Transactional
    public ResponseEntity<Optional<Instituicao>> findById(Long id) {
        System.out.println("[InstituicaoService] findById id=" + id);
        Optional<Instituicao> opt = instituicaoRepository.findById(id);
        log.debug("findById id={} -> presente={}", id, opt.isPresent());
        return ResponseEntity.ok(opt);
    }

    @Transactional
    public ResponseEntity<Optional<Instituicao>> findByNome(String nome) {
        System.out.println("[InstituicaoService] findByNome nome=" + nome);
        Optional<Instituicao> resultado = instituicaoRepository.findByNome(nome);
        log.debug("findByNome nome='{}' -> presente={}", nome, resultado.isPresent());
        return resultado.isPresent() ? ResponseEntity.ok(resultado) : ResponseEntity.notFound().build();
    }

    @Transactional
    public ResponseEntity<Optional<Instituicao>> findByEmail(String email) {
        System.out.println("[InstituicaoService] findByEmail email=" + email);
        Optional<Instituicao> resultado = instituicaoRepository.findByEmail(email);
        log.debug("findByEmail email='{}' -> presente={}", email, resultado.isPresent());
        return resultado.isPresent() ? ResponseEntity.ok(resultado) : ResponseEntity.notFound().build();
    }

    public Optional<Instituicao> buscarPorEmail(String email) {
        System.out.println("[InstituicaoService] buscarPorEmail email=" + email);
        Optional<Instituicao> opt = instituicaoRepository.findByEmail(email);
        log.debug("buscarPorEmail email='{}' -> presente={}", email, opt.isPresent());
        return opt;
    }

    @Transactional
    public Instituicao create(Instituicao instituicao) {
        System.out.println("[InstituicaoService] create nome=" + instituicao.getNome());
        try {
            if (instituicao.getSenha() != null) {
                log.debug("create -> codificando senha para email='{}'", instituicao.getEmail());
                instituicao.setSenha(passwordEncoder.encode(instituicao.getSenha()));
            }
            Instituicao saved = instituicaoRepository.save(instituicao);
            log.info("Instituição criada id={}, email='{}'", saved.getId(), saved.getEmail());
            System.out.println("[InstituicaoService] create OK id=" + saved.getId());
            return saved;
        } catch (Exception e) {
            log.error("Erro ao criar Instituicao: {}", e.getMessage(), e);
            System.out.println("[InstituicaoService] create ERRO: " + e.getMessage());
            throw new RuntimeException("Erro ao criar Instituicao: " + e.getMessage());
        }
    }

    @Transactional
    public Optional<Instituicao> update(Long id, Instituicao in) {
        System.out.println("[InstituicaoService] update id=" + id);
        return instituicaoRepository.findById(id).map(db -> {
            db.setCnpj(in.getCnpj());
            db.setEmail(in.getEmail());
            db.setNome(in.getNome());

            // Endereço
            db.setCep(in.getCep());
            db.setEndereco(in.getEndereco());
            db.setNumero(in.getNumero());
            db.setComplemento(in.getComplemento());
            db.setBairro(in.getBairro());
            db.setCidade(in.getCidade());
            db.setUf(in.getUf());

            // Arrays
            db.setAreaAtuacao(in.getAreaAtuacao());
            db.setCausasApoio(in.getCausasApoio());
            db.setHabilidadesRequeridas(in.getHabilidadesRequeridas());

            // Demais campos
            db.setTelefoneContato(in.getTelefoneContato());
            db.setDescription(in.getDescription());
            db.setResponsavelPreenchimento(in.getResponsavelPreenchimento());
            db.setNomeContatoVoluntariado(in.getNomeContatoVoluntariado());
            db.setFuncaoContatoVoluntariado(in.getFuncaoContatoVoluntariado());
            db.setTelefoneContatoVoluntariado(in.getTelefoneContatoVoluntariado());
            db.setSemFinsLucrativos(in.getSemFinsLucrativos());
            db.setConstituidaFormalmente(in.getConstituidaFormalmente());
            db.setEmAtividade(in.getEmAtividade());
            db.setSedeDesvinculada(in.getSedeDesvinculada());
            db.setPrestadoraServicos(in.getPrestadoraServicos());
            db.setInteresseRH(in.getInteresseRH());
            db.setPrestarInfosCEUB(in.getPrestarInfosCEUB());
            db.setAvaliadaCEUB(in.getAvaliadaCEUB());
            db.setMotivoInteresseVoluntarios(in.getMotivoInteresseVoluntarios());
            db.setEnderecoTrabalhoVoluntario(in.getEnderecoTrabalhoVoluntario());
            db.setHorasMensaisVoluntario(in.getHorasMensaisVoluntario());
            db.setContatosRepassadosVoluntarios(in.getContatosRepassadosVoluntarios());
            db.setComentariosSugestoes(in.getComentariosSugestoes());

            if (in.getSenha() != null && !in.getSenha().isBlank()) {
                log.debug("update id={} -> senha informada, codificando.", id);
                db.setSenha(passwordEncoder.encode(in.getSenha()));
            }

            Instituicao saved = instituicaoRepository.save(db);
            log.info("Instituição atualizada id={}", id);
            System.out.println("[InstituicaoService] update OK id=" + id);
            return saved;
        });
    }

    @Transactional
    public boolean delete(Long id) {
        System.out.println("[InstituicaoService] delete id=" + id);
        Optional<Instituicao> existente = instituicaoRepository.findById(id);
        if (existente.isPresent()) {
            instituicaoRepository.deleteById(id);
            log.info("Instituição deletada id={}", id);
            System.out.println("[InstituicaoService] delete OK id=" + id);
            return true;
        }
        log.warn("Tentativa de delete: instituição não encontrada id={}", id);
        System.out.println("[InstituicaoService] delete NOT FOUND id=" + id);
        return false;
    }

    @Transactional
    public String login(String email, String senha) {
        final long start = System.currentTimeMillis();
        System.out.println("[InstituicaoService] login email=" + email);
        log.info("Login attempt email='{}'", email);

        Optional<Instituicao> instituicaoOpt = instituicaoRepository.findByEmail(email);
        if (instituicaoOpt.isPresent()) {
            Instituicao instituicao = instituicaoOpt.get();
            boolean passwordOk = passwordEncoder.matches(senha, instituicao.getSenha());
            log.debug("Password match for email='{}'? {}", email, passwordOk);

            if (passwordOk) {
                String jwt = gerarJwtToken(instituicao);
                long took = System.currentTimeMillis() - start;
                log.info("Login success email='{}' ({}ms) - tokenLen={}", email, took, jwt != null ? jwt.length() : 0);
                System.out.println("[InstituicaoService] login OK (" + took + "ms)");
                return jwt;
            } else {
                long took = System.currentTimeMillis() - start;
                log.warn("Login failed (bad password) email='{}' ({}ms)", email, took);
                System.out.println("[InstituicaoService] login FAIL (senha) (" + took + "ms)");
                throw new RuntimeException("Senha incorreta.");
            }
        }
        long took = System.currentTimeMillis() - start;
        log.warn("Login failed (user not found) email='{}' ({}ms)", email, took);
        System.out.println("[InstituicaoService] login FAIL (usuario) (" + took + "ms)");
        throw new RuntimeException("Usuário não encontrado.");
    }

    @Transactional
    public ResponseEntity<Map<String, String>> register(Instituicao instituicao) {
        System.out.println("[InstituicaoService] register email=" + instituicao.getEmail());
        log.info("Register attempt email='{}'", instituicao.getEmail());

        Optional<Instituicao> existente = instituicaoRepository.findByEmail(instituicao.getEmail());
        if (existente.isPresent()) {
            log.warn("Register: email já registrado '{}'", instituicao.getEmail());
            System.out.println("[InstituicaoService] register FAIL (email duplicado)");
            return ResponseEntity.badRequest()
                    .body(Collections.singletonMap("message", "E-mail já registrado."));
        }

        try {
            if (instituicao.getSenha() != null) {
                log.debug("register -> codificando senha para email='{}'", instituicao.getEmail());
                instituicao.setSenha(passwordEncoder.encode(instituicao.getSenha()));
            }
            instituicaoRepository.save(instituicao);
            log.info("Register OK email='{}'", instituicao.getEmail());
            System.out.println("[InstituicaoService] register OK");
            return ResponseEntity.ok(Collections.singletonMap("message", "Usuário registrado com sucesso!"));
        } catch (Exception e) {
            log.error("Erro ao registrar '{}': {}", instituicao.getEmail(), e.getMessage(), e);
            System.out.println("[InstituicaoService] register ERRO: " + e.getMessage());
            return ResponseEntity.internalServerError()
                    .body(Collections.singletonMap("message", "Erro ao registrar: " + e.getMessage()));
        }
    }

    private String gerarJwtToken(Instituicao instituicao) {
        log.debug("Gerando JWT para instituicaoId={}", instituicao.getId());
        String token = jwtUtil.gerarToken(instituicao);
        // Não logar o token em si
        return token;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<Voluntario>> getVoluntariosDaInstituicao(Long id) {
        System.out.println("[InstituicaoService] getVoluntariosDaInstituicao id=" + id);
        return instituicaoRepository.findById(id)
                .map(inst -> {
                    List<Voluntario> lista = inst.getVoluntarios();
                    log.info("Voluntários da instituição id={} -> {} itens", id, lista != null ? lista.size() : 0);
                    return ResponseEntity.ok(lista);
                })
                .orElseGet(() -> {
                    log.warn("Instituição não encontrada ao listar voluntários id={}", id);
                    return ResponseEntity.notFound().build();
                });
    }
}
