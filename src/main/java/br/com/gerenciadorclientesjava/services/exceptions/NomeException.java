package br.com.gerenciadorclientesjava.services.exceptions;

public class NomeException extends Exception {

    public NomeException() {
        super("Nomes Proprios precisam ter mais de 10 caracteres");
    }
}
