package br.com.gerenciadorclientesjava.services.exceptions;

public class TipoPessoaException extends Exception {
    private String msg;

    public TipoPessoaException(String msg) {
        super(msg);
        this.msg = msg;
    }
}