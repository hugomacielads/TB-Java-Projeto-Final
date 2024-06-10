package com.olabi.babylonbank.config;

import com.olabi.babylonbank.model.entity.Cliente;
import com.olabi.babylonbank.repository.ClienteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class DatabaseInitializer implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DatabaseInitializer.class);

    private final ClienteRepository clienteRepository;

    public DatabaseInitializer(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public static final List<Cliente> clientes = List.of(
            new Cliente("Hugo", "12345678956", "hugo@email.com", 3500),
            new Cliente("Gabriela", "12345678923", "gabi@email.com", 2500),
            new Cliente("Tulio", "12323678956", "tulio@email.com", 1500)
    );

    @Override
    public void run(String... args) throws Exception {
        log.info("Alô pepe moreno? O banco ta conectado");
        log.info("Inserindo Clientes...");
        log.info("****************************");
        clienteRepository.saveAll(clientes);
        log.info("Clientes inseridos com sucesso!");
    }
}