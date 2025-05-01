package com.example.banco_digital.service;

import com.example.banco_digital.exception.ContaNaoEncontradaException;
import com.example.banco_digital.model.Conta;
import com.example.banco_digital.model.Transacao;
import com.example.banco_digital.repository.ContaRepository;
import com.example.banco_digital.repository.TransacaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

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

    @Transactional
    public Transacao sacar(String numeroConta, String agencia, BigDecimal valor) {
        Conta conta = contaRepository.findByNumeroContaAndAgencia(numeroConta, agencia)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));

        conta.debitar(valor); // Lança SaldoInsuficienteException se necessário

        Transacao transacao = new Transacao();
        transacao.setConta(conta);
        transacao.setValor(valor);
        transacao.setTipo("SAQUE");
        transacao.setDataHora(LocalDateTime.now());

        contaRepository.save(conta);
        return transacaoRepository.save(transacao);
    }

    @Transactional
    public Transacao transferir(String contaOrigem, String agenciaOrigem,
                                String contaDestino, String agenciaDestino,
                                BigDecimal valor) {
        // Busca contas
        Conta origem = contaRepository.findByNumeroContaAndAgencia(contaOrigem, agenciaOrigem)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta origem não encontrada"));

        Conta destino = contaRepository.findByNumeroContaAndAgencia(contaDestino, agenciaDestino)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta destino não encontrada"));

        // Executa operações
        origem.debitar(valor);
        destino.creditar(valor);

        // Cria transação de saída
        Transacao transacao = new Transacao();
        transacao.setConta(origem);
        transacao.setValor(valor);
        transacao.setTipo("TRANSFERENCIA");
        transacao.setDataHora(LocalDateTime.now());
        transacao.setContaDestino(contaDestino);
        transacao.setAgenciaDestino(agenciaDestino);

        // Salva alterações
        contaRepository.saveAll(List.of(origem, destino));
        return transacaoRepository.save(transacao);
    }
}
