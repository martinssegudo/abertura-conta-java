package br.com.gerenciadorclientesjava.services.exceptions;

public class RgDiferenteNoveCaracteresException extends Exception{

    public RgDiferenteNoveCaracteresException() {
        super("RG tem que ter 9 caracteres");
    }
}
