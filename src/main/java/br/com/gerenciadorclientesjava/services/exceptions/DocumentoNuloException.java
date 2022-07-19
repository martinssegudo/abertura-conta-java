package br.com.gerenciadorclientesjava.services.exceptions;

public class DocumentoNuloException extends Exception {
    public DocumentoNuloException() {
        super("Documento não pode ser nulo");
    }
}
