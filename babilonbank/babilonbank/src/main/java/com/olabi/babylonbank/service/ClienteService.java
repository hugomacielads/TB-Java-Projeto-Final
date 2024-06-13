package com.olabi.babylonbank.service;

import com.olabi.babylonbank.model.entity.*;
import com.olabi.babylonbank.repository.ClienteRepository;
import com.olabi.babylonbank.repository.ContaRepository;
import com.olabi.babylonbank.repository.TransacaoRepository;
import com.olabi.babylonbank.repository.TransacaoCreditoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;
    @Autowired
    private TransacaoCreditoRepository transacaoCreditoRepository;

    private void definirCategoria(Cliente cliente) {
        double renda = cliente.getRendaMensal();
        if (renda < 2000) {
            cliente.setCategoria(CategoriaCliente.BRONZE);
        } else if (renda <= 3000) {
            cliente.setCategoria(CategoriaCliente.PRATA);
        } else if (renda <= 5000) {
            cliente.setCategoria(CategoriaCliente.OURO);
        } else if (renda >= 5000){
            cliente.setCategoria(CategoriaCliente.PLATINUM);
        }
    }

    private void definirLimitesConta(Conta conta, double renda, CategoriaCliente categoria) {

        if (categoria == CategoriaCliente.PRATA) {
            conta.setChequeEspecial(renda * 0.1);
            conta.setLimiteCartaoCredito(renda * 0.3);

        } else if (categoria == CategoriaCliente.OURO) {
            conta.setChequeEspecial(renda * 0.3);
            conta.setLimiteCartaoCredito(renda * 0.6);
            conta.setLimiteFinanciamentoAprovado(renda * 0.3);

        } else if (categoria == CategoriaCliente.PLATINUM) {
            conta.setChequeEspecial(renda * 0.5);
            conta.setLimiteCartaoCredito(renda * 3);
            conta.setLimiteFinanciamentoAprovado(renda * 0.5);
            conta.setPorcentagemRetornoInvestimento(0.2);

        } else { // BRONZE
            conta.setChequeEspecial(0);
            conta.setLimiteCartaoCredito(0);
        }
    }

    @Transactional
    public Cliente cadastrarCliente(Cliente cliente) {
        definirCategoria(cliente);
        Conta conta = new Conta();
        conta.setCliente(cliente);
        conta.setSaldo(cliente.getRendaMensal());
        definirLimitesConta(conta, cliente.getRendaMensal(), cliente.getCategoria());
        contaRepository.save(conta);
        cliente.setConta(conta);
        cliente = clienteRepository.save(cliente);
        return cliente;
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    @Transactional
    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {

        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEmail(clienteAtualizado.getEmail());
        cliente.setRendaMensal(clienteAtualizado.getRendaMensal());
        definirCategoria(cliente);
        clienteRepository.save(cliente);

        Conta conta = cliente.getConta();
        conta.setSaldo(cliente.getRendaMensal());
        definirLimitesConta(conta, cliente.getRendaMensal(), cliente.getCategoria());
        contaRepository.save(conta);

        return cliente;
    }

    public Cliente buscarCliente(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Transactional
    public void realizarTransacao(Long contaId, Transacao transacao) {
        Conta conta = contaRepository.findById(contaId).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        transacao.setConta(conta);

        if (transacao.getTipo() == TipoTransacao.DEBITO) {
            if (conta.getSaldo() + conta.getChequeEspecial() < transacao.getValor()) {
                throw new RuntimeException("Saldo insuficiente");
            }
            conta.setSaldo(conta.getSaldo() - transacao.getValor());
        } else if (transacao.getTipo() == TipoTransacao.CREDITO) {
            conta.setSaldo(conta.getSaldo() + transacao.getValor());
        } else {
            throw new RuntimeException("Tipo de transação inválido");
        }

        transacaoRepository.save(transacao);
        contaRepository.save(conta);
    }

    public List<Transacao> listarTransacoes(Long contaId) {
        Conta conta = contaRepository.findById(contaId).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        return conta.getTransacoes();
    }

    public void realizarTransacaoCredito(Long contaId, TransacaoCredito transacaoCredito) {
        Conta conta = contaRepository.findById(contaId).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        transacaoCredito.setConta(conta);
        if (conta.getLimiteCartaoCredito() > transacaoCredito.getValor()) {
            conta.setFaturaCartaoCredito(conta.getFaturaCartaoCredito() + transacaoCredito.getValor());
            conta.setLimiteFinanciamentoAprovado(conta.getLimiteCartaoCredito() - transacaoCredito.getValor());
        } else {
            throw new RuntimeException("Saldo do Cartão de crédito insuficiente");
        }

        transacaoCreditoRepository.save(transacaoCredito);
        contaRepository.save(conta);
    }

    public List<TransacaoCredito> listarTransacoesCredito(Long contaId) {
        Conta conta = contaRepository.findById(contaId).orElseThrow(() -> new RuntimeException("Conta não encontrada"));
        return conta.getTransacoesCredito();
    }
}