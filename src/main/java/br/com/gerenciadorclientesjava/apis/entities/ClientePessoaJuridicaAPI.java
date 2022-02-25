package br.com.gerenciadorclientesjava.apis.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientePessoaJuridicaAPI {

    private Long idCliente;
    private Integer tipoCliente;
    private String cliente;
    private String cnpj;
    private Integer serasa;
    private LoginAPI login;
    private String dataAbertura;
}
