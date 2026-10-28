package com.codequest.api.controller;

import com.codequest.api.model.Questao;
import com.codequest.api.repository.QuestaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/questoes")
@CrossOrigin("*")
public class QuestaoController {

    @Autowired
    private QuestaoRepository repository;

    // LER - Questões Globais (do sistema)
    @GetMapping("/globais")
    public List<Questao> listarGlobais() {
        return repository.findByTurmaIdIsNull();
    }

    // LER - Questões específicas de uma turma
    @GetMapping("/turma/{turmaId}")
    public List<Questao> listarPorTurma(@PathVariable Long turmaId) {
        return repository.findByTurmaId(turmaId);
    }

    // CRIAR - Registar uma nova questão
    @PostMapping
    public Questao criarQuestao(@RequestBody Questao questao) {
        return repository.save(questao);
    }

    // ATUALIZAR - Corrigir enunciado ou alternativas de uma questão
    @PutMapping("/{id}")
    public ResponseEntity<Questao> atualizarQuestao(@PathVariable Long id, @RequestBody Questao questaoAtualizada) {
        Optional<Questao> questaoExistente = repository.findById(id);
        if (questaoExistente.isPresent()) {
            Questao obj = questaoExistente.get();
            obj.setEnunciado(questaoAtualizada.getEnunciado());
            obj.setAlternativaCerta(questaoAtualizada.getAlternativaCerta());
            obj.setAlternativaErrada1(questaoAtualizada.getAlternativaErrada1());
            obj.setAlternativaErrada2(questaoAtualizada.getAlternativaErrada2());
            obj.setAlternativaErrada3(questaoAtualizada.getAlternativaErrada3());
            return ResponseEntity.ok(repository.save(obj));
        }
        return ResponseEntity.notFound().build();
    }

    // DELETAR - Eliminar uma questão
    @DeleteMapping("/{id}")
    public void deletarQuestao(@PathVariable Long id) {
        repository.deleteById(id);
    }
}