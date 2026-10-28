package com.codequest.api.controller;

import com.codequest.api.model.Usuario;
import com.codequest.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    // LER/READ
    @GetMapping
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    // CRIAR/CREAT - Cadastra usuário
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    // DELETAR/DELETEA - Apaga usuário
    @DeleteMapping("/{id}")
    public void deletarUsuario(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // ATUALIZAR/UPDATE - Atualiza os dados de um usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioExistente = repository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario obj = usuarioExistente.get();
            obj.setNome(usuarioAtualizado.getNome());
            obj.setEmail(usuarioAtualizado.getEmail());
            obj.setSenha(usuarioAtualizado.getSenha());
            return ResponseEntity.ok(repository.save(obj));
        }
        return ResponseEntity.notFound().build();
    }

    // LOGIN - Verifica se o email e a senha batem com o banco de dados
    @PostMapping("/login")
    public ResponseEntity<Usuario> fazerLogin(@RequestBody Usuario dadosLogin) {
        Usuario usuario = repository.findByEmail(dadosLogin.getEmail());

        // Verifica se achou o email e se a senha está correta
        if (usuario != null && usuario.getSenha().equals(dadosLogin.getSenha())) {
            return ResponseEntity.ok(usuario); // Login funciona
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build(); // Senha errada ou email não existe
    }
}