package com.olabi.babylonbank.repository;

import com.olabi.babylonbank.model.entity.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
}