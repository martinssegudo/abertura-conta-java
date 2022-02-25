package br.com.gerenciadorclientesjava.apis.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaPessoaJuridicaAPI{

    private Long numeroConta;
    private String tipoConta;
    private ClientePessoaJuridicaAPI clientePessoaJuridicaAPI;
    private String erro;
}