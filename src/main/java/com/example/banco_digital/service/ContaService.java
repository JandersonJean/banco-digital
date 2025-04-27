package com.example.banco_digital.service;

import com.example.banco_digital.exception.ContaNaoEncontradaException;
import com.example.banco_digital.model.Conta;
import com.example.banco_digital.repository.ContaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor //Lombok para injetar dependências
public class ContaService {
    private final ContaRepository contaRepository;

    public Conta criarConta(Conta conta) {
        return contaRepository.save(conta);
    }

    public Conta consultarSaldo(String numeroConta, String agencia) {
        return contaRepository.findByNumeroContaAndAgencia(numeroConta, agencia)
                .orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada"));
    }
}
