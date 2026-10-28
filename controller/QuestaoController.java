package com.codequest.api.controller;

import com.codequest.api.model.Questao;
import com.codequest.api.repository.QuestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questoes")
@CrossOrigin("*")
public class QuestaoController {

    @Autowired
    private QuestaoRepository repository;

    @GetMapping("/globais")
    public List<Questao> listarGlobais() {
        return repository.findByTurmaIdIsNull();
    }

    @GetMapping("/turma/{turmaId}")
    public List<Questao> listarPorTurma(@PathVariable Long turmaId) {
        return repository.findByTurmaId(turmaId);
    }

    @PostMapping
    public Questao criarQuestao(@RequestBody Questao questao) {
        return repository.save(questao);
    }
}