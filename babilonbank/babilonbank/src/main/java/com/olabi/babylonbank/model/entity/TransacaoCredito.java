package com.olabi.babylonbank.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class TransacaoCredito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double valor;
    private int parcelas;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    @JsonBackReference
    private Conta conta;
}
