package com.codequest.api.repository;

import com.codequest.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Spring cria o SQL automático para buscar por email
    Usuario findByEmail(String email);
}