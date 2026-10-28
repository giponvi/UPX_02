package com.codequest.api.repository;

import com.codequest.api.model.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {

    // Busca todas as matrículas de um aluno pra ver as turmas que ele esta
    List<Matricula> findByAlunoId(Long alunoId);

    // Busca todas as matrículas de uma turma pra professor ver os aluno
    List<Matricula> findByTurmaId(Long turmaId);

    // Verifica se o aluno já está matriculado naquela turma para não duplicar
    boolean existsByAlunoIdAndTurmaId(Long alunoId, Long turmaId);
}