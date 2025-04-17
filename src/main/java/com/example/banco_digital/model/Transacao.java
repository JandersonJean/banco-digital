package com.example.banco_digital.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Entity
@Getter
@Setter
public class Transacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    private LocalDateTime dataHora;
    private String tipo; // DEPOSITO, SAQUE, TRANSFERENCIA

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    // Dados para transferência
    private String contaDestino;
    private String agenciaDestino;
}
