package com.codequest.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "questoes")
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String enunciado;

    private String alternativaCerta;
    private String alternativaErrada1;
    private String alternativaErrada2;
    private String alternativaErrada3;

    // Se for null, é uma questão global do sistema. Se tiver um ID, é uma questão específica de uma turma.
    private Long turmaId;
}