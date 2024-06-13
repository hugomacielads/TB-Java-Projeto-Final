package com.olabi.babylonbank.controller;

import com.olabi.babylonbank.model.entity.Cliente;
import com.olabi.babylonbank.model.entity.Transacao;
import com.olabi.babylonbank.model.entity.TransacaoCredito;
import com.olabi.babylonbank.repository.ClienteRepository;
import com.olabi.babylonbank.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private static final Logger log = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        return clienteService.cadastrarCliente(cliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        try {
            List<Cliente> clientes = clienteService.listarClientes();
            return ResponseEntity.ok(clientes);
        } catch (Exception e) {
            log.error("Erro ao buscar os Clientes");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @DeleteMapping("/{id}/delete")
    public void delete(@PathVariable Long id){
        clienteService.deletarCliente(id);
    }
}
