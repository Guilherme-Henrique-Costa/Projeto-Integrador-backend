package com.example.voluntariadointeligentehub.services;

import com.example.voluntariadointeligentehub.entities.FeedbackVoluntario;
import com.example.voluntariadointeligentehub.repositories.FeedbackVoluntarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackVoluntarioService {

    private static final Logger log = LoggerFactory.getLogger(FeedbackVoluntarioService.class);

    @Autowired
    private FeedbackVoluntarioRepository repo;

    @Transactional(readOnly = true)
    public ResponseEntity<List<FeedbackVoluntario>> findAll() {
        System.out.println("[FeedbackVoluntarioService] findAll()");
        List<FeedbackVoluntario> list = repo.findAll();
        log.debug("findAll -> {} registros", list.size());
        return ResponseEntity.ok(list);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Optional<FeedbackVoluntario>> findById(Long id) {
        System.out.println("[FeedbackVoluntarioService] findById id=" + id);
        Optional<FeedbackVoluntario> opt = repo.findById(id);
        log.debug("findById({}) -> present={}", id, opt.isPresent());
        return ResponseEntity.ok(opt);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<FeedbackVoluntario>> findByVoluntario(Long voluntarioId) {
        System.out.println("[FeedbackVoluntarioService] findByVoluntario voluntarioId=" + voluntarioId);
        List<FeedbackVoluntario> list = repo.findByVoluntario_Id(voluntarioId);
        log.info("findByVoluntario({}) -> {} itens", voluntarioId, list.size());
        return ResponseEntity.ok(list);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<List<FeedbackVoluntario>> search(String q, Long voluntarioId) {
        long start = System.currentTimeMillis();
        System.out.println("[FeedbackVoluntarioService] search q=" + q + " voluntarioId=" + voluntarioId);
        List<FeedbackVoluntario> list = repo.search(q, voluntarioId);
        long took = System.currentTimeMillis() - start;
        log.info("search(q='{}', voluntarioId={}) -> {} itens ({}ms)", q, voluntarioId, list.size(), took);
        return ResponseEntity.ok(list);
    }

    @Transactional
    public FeedbackVoluntario create(FeedbackVoluntario body) {
        System.out.println("[FeedbackVoluntarioService] create voluntarioId="
                + (body.getVoluntario() != null ? body.getVoluntario().getId() : null));
        FeedbackVoluntario saved = repo.save(body);
        log.info("Feedback criado id={} voluntarioId={}",
                saved.getId(), saved.getVoluntario() != null ? saved.getVoluntario().getId() : null);
        System.out.println("[FeedbackVoluntarioService] create OK id=" + saved.getId());
        return saved;
    }

    @Transactional
    public Optional<FeedbackVoluntario> update(Long id, FeedbackVoluntario details) {
        System.out.println("[FeedbackVoluntarioService] update id=" + id);
        return repo.findById(id).map(f -> {
            f.setDescricaoVaga(details.getDescricaoVaga());
            f.setFeedback(details.getFeedback());
            f.setVoluntario(details.getVoluntario());
            FeedbackVoluntario saved = repo.save(f);
            log.info("Feedback atualizado id={}", id);
            System.out.println("[FeedbackVoluntarioService] update OK id=" + id);
            return saved;
        });
    }

    @Transactional
    public boolean delete(Long id) {
        System.out.println("[FeedbackVoluntarioService] delete id=" + id);
        Optional<FeedbackVoluntario> existing = repo.findById(id);
        if (existing.isPresent()) {
            repo.deleteById(id);
            log.info("Feedback deletado id={}", id);
            System.out.println("[FeedbackVoluntarioService] delete OK id=" + id);
            return true;
        }
        log.warn("Tentativa de deletar feedback inexistente id={}", id);
        System.out.println("[FeedbackVoluntarioService] delete NOT FOUND id=" + id);
        return false;
    }
}
