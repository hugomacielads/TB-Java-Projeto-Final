package com.olabi.babylonbank.model.entity;

import jakarta.persistence.*;

@Entity
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valor;

    @Enumerated(EnumType.STRING)
    private TipoTransacao tipo;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    // getters e setters
}
