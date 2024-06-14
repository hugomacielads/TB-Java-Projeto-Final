package com.olabi.babylonbank.controller;

import com.olabi.babylonbank.model.entity.Cliente;
import com.olabi.babylonbank.model.entity.Transacao;
import com.olabi.babylonbank.model.entity.TransacaoCredito;
import com.olabi.babylonbank.repository.ClienteRepository;
import com.olabi.babylonbank.service.ClienteService;
import com.olabi.babylonbank.exception.ClienteException;
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
    @CrossOrigin(origins="*") // API pode ser acessada de qualquer IP
    public ResponseEntity<Object> cadastrarCliente(@RequestBody Cliente clienteBody) {
        try {
            Cliente cliente = clienteService.cadastrarCliente(clienteBody);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        } catch (ClienteException.DuplicateCpfException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    @CrossOrigin(origins="*") // API pode ser acessada de qualquer IP
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
    @CrossOrigin(origins="*") // API pode ser acessada de qualquer IP
    public Cliente atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.atualizarCliente(id, cliente);
    }

    @GetMapping("/{id}")
    @CrossOrigin(origins="*") // API pode ser acessada de qualquer IP
    public Cliente buscarCliente(@PathVariable Long id) {
        return clienteService.buscarCliente(id);
    }

    @PostMapping("/{id}/transacoes")
    @CrossOrigin(origins="*") // API pode ser acessada de qualquer IP
    public void realizarTransacao(@PathVariable Long id, @RequestBody Transacao transacao) {
        clienteService.realizarTransacao(id, transacao);
    }

    @GetMapping("/{id}/transacoes")
    @CrossOrigin(origins="*") // API pode ser acessada de qualquer IP
    public List<Transacao> listarTransacoes(@PathVariable Long id) {
        return clienteService.listarTransacoes(id);
    }

    @PostMapping("/{id}/transacoesCredito")
    @CrossOrigin(origins="*") // API pode ser acessada de qualquer IP
    public void realizarTransacaoCredito(@PathVariable Long id, @RequestBody TransacaoCredito transacaoCredito) {
        clienteService.realizarTransacaoCredito(id, transacaoCredito);
    }

    @GetMapping("/{id}/transacoesCredito")
    @CrossOrigin(origins="*") // API pode ser acessada de qualquer IP
    public List<TransacaoCredito> listarTransacoesCredito(@PathVariable Long id) {
        return clienteService.listarTransacoesCredito(id);
    }

    @DeleteMapping("/{id}/delete")
    @CrossOrigin(origins="*") // API pode ser acessada de qualquer IP
    public void delete(@PathVariable Long id){
        clienteService.deletarCliente(id);
    }
}
