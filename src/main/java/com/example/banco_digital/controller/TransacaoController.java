package com.example.banco_digital.controller;

import com.example.banco_digital.model.Transacao;
import com.example.banco_digital.service.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/transacoes")
@RequiredArgsConstructor
public class TransacaoController {
    private final TransacaoService transacaoService;

    @PostMapping("/deposito")
    public ResponseEntity<Transacao> depositar(
            @RequestParam String numeroConta,
            @RequestParam String agencia,
            @RequestParam BigDecimal valor) {
        return ResponseEntity.ok(transacaoService.depositar(numeroConta, agencia, valor));
    }

    // Implementar endpoints para saque e transferência
}
