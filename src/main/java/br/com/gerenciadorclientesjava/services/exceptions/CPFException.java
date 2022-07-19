package br.com.gerenciadorclientesjava.services.exceptions;

public class CPFException extends Exception{
    public CPFException() {
        super("CPF Invalido");
    }
}
