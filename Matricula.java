package com.codequest.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "matriculas")
public class Matricula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Guardamos só os IDs
    private Long alunoId;
    private Long turmaId;
}