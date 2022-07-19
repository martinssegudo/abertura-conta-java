package br.com.gerenciadorclientesjava.services.exceptions;

public class SenhaMenorSeisCaracteresException extends Exception {
    public SenhaMenorSeisCaracteresException() {
        super("Senha menor que 6 caracteres");
    }
}
