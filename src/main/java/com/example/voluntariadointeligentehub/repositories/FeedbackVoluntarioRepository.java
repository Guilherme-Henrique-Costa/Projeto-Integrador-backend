package com.example.voluntariadointeligentehub.repositories;

import com.example.voluntariadointeligentehub.entities.FeedbackVoluntario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface FeedbackVoluntarioRepository extends JpaRepository<FeedbackVoluntario, Long> {

    List<FeedbackVoluntario> findByVoluntario_Id(Long voluntarioId);

    List<FeedbackVoluntario> findByFeedbackContainingIgnoreCase(String q);

    @Query("""
        select f from FeedbackVoluntario f
        join f.voluntario v
        where
            (:q is null or :q = '' or
             lower(coalesce(f.descricaoVaga,'')) like lower(concat('%', :q, '%')) or
             lower(coalesce(f.feedback,''))      like lower(concat('%', :q, '%')) or
             lower(coalesce(v.nome,''))          like lower(concat('%', :q, '%')) or
             lower(coalesce(v.emailInstitucional,'')) like lower(concat('%', :q, '%'))
            )
            and (:volId is null or v.id = :volId)
        order by f.id desc
    """)
    List<FeedbackVoluntario> search(@Param("q") String q, @Param("volId") Long volId);
}
