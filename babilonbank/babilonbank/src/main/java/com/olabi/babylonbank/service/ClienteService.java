package com.olabi.babylonbank.service;

import com.olabi.babylonbank.model.entity.Cliente;
import com.olabi.babylonbank.repository.ClienteRepository;
import com.olabi.babylonbank.repository.ContaRepository;
import com.olabi.babylonbank.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private TransacaoRepository transacaoRepository;
    
}