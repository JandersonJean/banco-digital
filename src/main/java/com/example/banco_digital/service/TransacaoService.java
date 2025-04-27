package com.example.banco_digital.service;

import com.example.banco_digital.exception.ContaNaoEncontradaException;
import com.example.banco_digital.model.Conta;
import com.example.banco_digital.model.Transacao;
import com.example.banco_digital.repository.ContaRepository;
import com.example.banco_digital.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransacaoService {
    private final ContaRepository contaRepository;
    private final TransacaoRepository transacaoRepository;

    public Transacao depositar(String numeroConta, String agencia, BigDecimal valor) {
        Conta conta = contaRepository.findByNumeroContaAndAgencia(numeroConta, agencia)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));

        conta.creditar(valor);

        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setValor(valor);
        transacao.setTipo("DEPOSITO");
        transacao.setDataHora(LocalDateTime.now());

        contaRepository.save(conta);
        return transacaoRepository.save(transacao);
    }

    // Implementar métodos para saque e transferência
}
