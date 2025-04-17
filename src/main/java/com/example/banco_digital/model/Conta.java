package com.example.banco_digital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Conta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroConta;
    private String agencia;
    private BigDecimal saldo;
    private String tipoConta; // CORRENTE, POUPANCA
    private String titular;

    // Métodos para crédito/débito
    public void creditar(BigDecimal valor) {
        saldo = saldo.add(valor);
    }
    public void debitar(BigDecimal valor) {
        saldo = saldo.subtract(valor);
    }

    @OneToMany(mappedBy = "conta", cascade = CascadeType.ALL)
    private List<Transacao> transacoes = new ArrayList<>();
}
