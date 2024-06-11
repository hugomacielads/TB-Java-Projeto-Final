package com.olabi.babylonbank.service;

import com.olabi.babylonbank.model.entity.CategoriaCliente;
import com.olabi.babylonbank.model.entity.Cliente;
import com.olabi.babylonbank.model.entity.Conta;
import com.olabi.babylonbank.repository.ClienteRepository;
import com.olabi.babylonbank.repository.ContaRepository;
import com.olabi.babylonbank.repository.TransacaoRepository;
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
            // conta.setLimiteParcelaFinanciamento(renda * 0.3);

        } else if (categoria == CategoriaCliente.PLATINUM) {
            conta.setChequeEspecial(renda * 0.5);
            conta.setLimiteCartaoCredito(Double.MAX_VALUE);
            // conta.setLimiteParcelaFinanciamento(renda * 0.3);
            // conta.setLiberarInvestimento("S");

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
}