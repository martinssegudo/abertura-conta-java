package br.com.gerenciadorclientesjava.services.exceptions;

public class ValidaDocumentoPessoaContaException extends Exception{

        public ValidaDocumentoPessoaContaException() {
            super("Pessoa ou Conta Incorreta para esse Documento");
    }
}
