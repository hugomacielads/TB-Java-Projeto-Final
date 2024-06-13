package com.olabi.babylonbank.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double saldo;
    private double chequeEspecial;
    private double limiteCartaoCredito;
    private double faturaCartaoCredito;
    private double limiteFinanciamentoAprovado;
    private double porcentagemRetornoInvestimento;

    @OneToOne
    @JoinColumn(name = "cliente_id")
    @JsonBackReference
    private Cliente cliente;

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Transacao> transacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getChequeEspecial() {
        return chequeEspecial;
    }

    public void setChequeEspecial(double chequeEspecial) {
        this.chequeEspecial = chequeEspecial;
    }

    public double getLimiteCartaoCredito() {
        return limiteCartaoCredito;
    }

    public void setLimiteCartaoCredito(double limiteCartaoCredito) {
        this.limiteCartaoCredito = limiteCartaoCredito;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public void setTransacoes(List<Transacao> transacoes) {
        this.transacoes = transacoes;
    }

// getters e setters
}
