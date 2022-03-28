package br.com.gerenciadorclientesjava.services.exceptions;

public class DocumentoException extends Exception {

    public DocumentoException() {
        super("Documento deve ter 11 ou 14 digitos");


    }

}
