package com.codequest.api.repository;

import com.codequest.api.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    // Para o aluno encontrar a turma só digitando o código dela
    Turma findByCodigoAcesso(String codigoAcesso);

    // Para listar todas as turmas que um professor criou
    List<Turma> findByProfessorId(Long professorId);
}