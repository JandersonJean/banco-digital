package com.example.banco_digital.exception;

import java.io.Serializable;

public class ContaNaoEncontradaException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;

    public ContaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    // Opcional: para incluir a causa raiz
    public ContaNaoEncontradaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
