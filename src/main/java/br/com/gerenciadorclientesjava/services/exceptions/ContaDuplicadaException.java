package br.com.gerenciadorclientesjava.services.exceptions;

public class ContaDuplicadaException extends Exception{

    public ContaDuplicadaException() {
        super("Conta Duplicada");
    }

}
