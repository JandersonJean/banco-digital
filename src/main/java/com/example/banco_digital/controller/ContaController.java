package com.example.banco_digital.controller;

import com.example.banco_digital.model.Conta;
import com.example.banco_digital.service.ContaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class ContaController {
    private final ContaService contaService;

    @PostMapping
    public ResponseEntity<Conta> criarConta(@RequestBody Conta conta) {
        return ResponseEntity.ok(contaService.criarConta(conta));
    }

    @GetMapping("/{numeroConta}/{agencia}/saldo")
    public ResponseEntity<BigDecimal> consultarSaldo(
            @PathVariable String numeroConta,
            @PathVariable String agencia) {
        Conta conta = contaService.consultarSaldo(numeroConta, agencia);
        return ResponseEntity.ok(conta.getSaldo());
    }
}
