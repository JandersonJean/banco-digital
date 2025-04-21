package com.example.banco_digital.repository;

import com.example.banco_digital.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContaRepository extends JpaRepository<Conta, Long> {
    Optional<Conta> findByNumeroContaAndAgencia(String numeroConta, String agencia);
}
