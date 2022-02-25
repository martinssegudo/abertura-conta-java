package br.com.gerenciadorclientesjava.apis.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaPessoaFisicaAPI{

    private Long numeroConta;
    private String tipoConta;
    private ClientePessoaFisicaAPI clientePessoaFisicaAPI;
    private String erro;
}