package br.com.gerenciadorclientesjava.services.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LoginException extends Exception {

    public LoginException() {
        super("Login incorreto / invalido, ou conta inexistente");
    }
}
