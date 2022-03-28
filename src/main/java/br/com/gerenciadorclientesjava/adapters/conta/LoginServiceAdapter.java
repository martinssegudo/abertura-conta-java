package br.com.gerenciadorclientesjava.adapters.conta;

import br.com.gerenciadorclientesjava.db.entities.LoginEntity;
import br.com.gerenciadorclientesjava.services.entities.Login;
import lombok.Data;

@Data
public class LoginServiceAdapter {

    private Login login;

    public LoginServiceAdapter(LoginEntity loginEntity) {
        setLogin(convertLoginEntityEmLogin(loginEntity));
    }

    private Login convertLoginEntityEmLogin(LoginEntity loginEntity) {
        return Login.builder()
                .senha(loginEntity.getSenha())
                .build();
    }
}