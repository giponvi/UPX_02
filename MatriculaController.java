package com.codequest.api.controller;

import com.codequest.api.model.Matricula;
import com.codequest.api.repository.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matriculas")
@CrossOrigin("*")
public class MatriculaController {

    @Autowired
    private MatriculaRepository repository;

    // Aluno se matricular numa turma
    @PostMapping
    public ResponseEntity<?> criarMatricula(@RequestBody Matricula matricula) {
        // Se o aluno já estiver nessa turma, a gente bloqueia
        if (repository.existsByAlunoIdAndTurmaId(matricula.getAlunoId(), matricula.getTurmaId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Aluno já matriculado nesta turma!");
        }
        return ResponseEntity.ok(repository.save(matricula));
    }

    // Listar as turmas em que um aluno está matriculado
    @GetMapping("/aluno/{alunoId}")
    public List<Matricula> listarPorAluno(@PathVariable Long alunoId) {
        return repository.findByAlunoId(alunoId);
    }

    // Professor remover o aluno da turma ou aluno sair da turma
    @DeleteMapping("/{id}")
    public void deletarMatricula(@PathVariable Long id) {
        repository.deleteById(id);
    }
}