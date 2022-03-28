package br.com.gerenciadorclientesjava.services.exceptions;

public class DataMenorException extends Exception{

    public DataMenorException () {
        super("Idade Menor que 18 anos / Data Invalida");
    }
}
