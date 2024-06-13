package com.olabi.babylonbank.controller;

import com.olabi.babylonbank.model.entity.Cliente;
import com.olabi.babylonbank.model.entity.Transacao;
import com.olabi.babylonbank.model.entity.TransacaoCredito;
import com.olabi.babylonbank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        return clienteService.cadastrarCliente(cliente);
    }

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarClientes();
    }

    @PutMapping("/{id}")
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.atualizarCliente(id, cliente);
    }

    @GetMapping("/{id}")
    public Cliente buscarCliente(@PathVariable Long id) {
        return clienteService.buscarCliente(id);
    }

    @PostMapping("/{id}/transacoes")
    public void realizarTransacao(@PathVariable Long id, @RequestBody Transacao transacao) {
        clienteService.realizarTransacao(id, transacao);
    }

    @GetMapping("/{id}/transacoes")
    public List<Transacao> listarTransacoes(@PathVariable Long id) {
        return clienteService.listarTransacoes(id);
    }

    @PostMapping("/{id}/transacoesCredito")
    public void realizarTransacaoCredito(@PathVariable Long id, @RequestBody TransacaoCredito transacaoCredito) {
        clienteService.realizarTransacaoCredito(id, transacaoCredito);
    }

    @GetMapping("/{id}/transacoesCredito")
    public List<TransacaoCredito> listarTransacoesCredito(@PathVariable Long id) {
        return clienteService.listarTransacoesCredito(id);
    }
}
