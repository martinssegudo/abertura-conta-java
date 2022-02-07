package br.com.gerenciadorclientesjava.services.exceptions;

public class ContaException extends Exception {
    private String msg;

    public ContaException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
