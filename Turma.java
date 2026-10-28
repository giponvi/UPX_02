package com.codequest.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "turmas")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String codigoAcesso; // Código que o aluno usa para entrar

    // Guardamos apenas o ID do professor que criou a turma.
    private Long professorId;
}