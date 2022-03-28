package br.com.gerenciadorclientesjava.services.exceptions;

public class RgNuloException extends Exception {

    public RgNuloException() {
        super("RG não pode ser nulo");
    }
}
