package br.com.gerenciadorclientesjava.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContaInexistenteException extends Exception{

    public ContaInexistenteException() {
        super("Conta Inexistente");
    }

}
