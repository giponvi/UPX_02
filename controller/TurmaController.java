package com.codequest.api.controller;

import com.codequest.api.model.Turma;
import com.codequest.api.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turmas")
@CrossOrigin("*")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    @GetMapping
    public List<Turma> listarTodas() {
        return repository.findAll();
    }

    @GetMapping("/professor/{professorId}")
    public List<Turma> listarPorProfessor(@PathVariable Long professorId) {
        return repository.findByProfessorId(professorId);
    }

    @PostMapping
    public Turma criarTurma(@RequestBody Turma turma) {
        return repository.save(turma);
    }
}