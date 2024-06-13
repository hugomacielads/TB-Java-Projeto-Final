package com.olabi.babylonbank.repository;

import com.olabi.babylonbank.model.entity.TransacaoCredito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoCreditoRepository extends JpaRepository<TransacaoCredito, Long> {
}
