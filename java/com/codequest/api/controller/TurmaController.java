package com.codequest.api.controller;

import com.codequest.api.model.Turma;
import com.codequest.api.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/turmas")
@CrossOrigin("*")
public class TurmaController {

    @Autowired
    private TurmaRepository repository;

    // LER - Listar todas as turmas
    @GetMapping
    public List<Turma> listarTodas() {
        return repository.findAll();
    }

    // LER professor- Listar turmas criadas por um professor específico
    @GetMapping("/professor/{professorId}")
    public List<Turma> listarPorProfessor(@PathVariable Long professorId) {
        return repository.findByProfessorId(professorId);
    }

    // CRIAR - Cadastrar nova turma
    @PostMapping
    public Turma criarTurma(@RequestBody Turma turma) {
        return repository.save(turma);
    }

    // ATUALIZAR - Alterar os dados de uma turma
    @PutMapping("/{id}")
    public ResponseEntity<Turma> atualizarTurma(@PathVariable Long id, @RequestBody Turma turmaAtualizada) {
        Optional<Turma> turmaExistente = repository.findById(id);
        if (turmaExistente.isPresent()) {
            Turma obj = turmaExistente.get();
            obj.setNome(turmaAtualizada.getNome());
            obj.setCodigoAcesso(turmaAtualizada.getCodigoAcesso());
            return ResponseEntity.ok(repository.save(obj));
        }
        return ResponseEntity.notFound().build();
    }

    // DELETAR - Apagar uma turma
    @DeleteMapping("/{id}")
    public void deletarTurma(@PathVariable Long id) {
        repository.deleteById(id);
    }
}