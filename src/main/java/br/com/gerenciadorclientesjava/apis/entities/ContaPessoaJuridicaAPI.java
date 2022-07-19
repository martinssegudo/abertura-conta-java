package br.com.gerenciadorclientesjava.apis.entities;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaPessoaJuridicaAPI{

    private Long numeroConta;

    @NotNull
    @Size(max = 1)
    private String tipoConta;

    private ClientePessoaJuridicaAPI clientePessoaJuridicaAPI;

}