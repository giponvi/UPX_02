package com.codequest.api.repository;

import com.codequest.api.model.Questao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestaoRepository extends JpaRepository<Questao, Long> {
    // Busca as questões específicas que um professor criou para uma turma
    List<Questao> findByTurmaId(Long turmaId);

    // Busca as questões globais do sistema
    List<Questao> findByTurmaIdIsNull();
}