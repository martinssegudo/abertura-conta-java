package br.com.gerenciadorclientesjava.services.exceptions;

public class TipoContaException extends Exception {
    private String msg;

    public TipoContaException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
