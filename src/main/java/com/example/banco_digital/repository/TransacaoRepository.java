package com.example.banco_digital.repository;

import com.example.banco_digital.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByContaIdOrderByDataHoraDesc(Long contaId);
}
