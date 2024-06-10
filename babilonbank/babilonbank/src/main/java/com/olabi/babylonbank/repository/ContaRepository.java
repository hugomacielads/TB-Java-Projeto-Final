package com.olabi.babylonbank.repository;

import com.olabi.babylonbank.model.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaRepository extends JpaRepository<Conta, Long> {
}