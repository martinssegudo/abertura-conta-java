package br.com.gerenciadorclientesjava.services.exceptions;

public class SenhaInvalidaException extends Exception {
    public SenhaInvalidaException() {
        super("Senha Invalida");
    }
}
