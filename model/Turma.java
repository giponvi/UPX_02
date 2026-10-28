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

    private String codigoAcesso; // O código que o aluno usa para entrar

    // Em vez de uma relação complexa, guardamos apenas o ID do professor que criou a turma. Simples e funcional!
    private Long professorId;
}