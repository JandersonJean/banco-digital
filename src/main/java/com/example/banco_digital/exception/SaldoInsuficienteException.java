package com.example.banco_digital.exception;

import java.io.Serializable;

public class SaldoInsuficienteException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    public SaldoInsuficienteException(String mensagem) {
        super(mensagem);
    }

    public SaldoInsuficienteException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
