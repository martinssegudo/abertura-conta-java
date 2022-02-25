package br.com.gerenciadorclientesjava.apis.entities;

import br.com.gerenciadorclientesjava.services.entities.Cliente;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginAPI {

    private Long id;
    private String senha;
}