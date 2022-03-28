package br.com.gerenciadorclientesjava.services.exceptions;

public class DataParseException extends Exception {

    public DataParseException () {
        super("Idade Menor que 18 anos");
    }

}
