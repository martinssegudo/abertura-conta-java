package br.com.gerenciadorclientesjava.apis.entities;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientePessoaJuridicaAPI {

    private Long idCliente;

    @NotNull
    @Size(max = 1)
    private Integer tipoCliente;

    @NotNull
    @Size(min = 1, max = 200)
    private String cliente;

    @NotNull
    @Size(min = 14, max = 14)
    private String cnpj;

    @Max(1000)
    @Size(min = 1, max = 4)
    private Integer serasa;

    private LoginAPI login;

    @NotNull
    @Size(min = 10, max = 10)
    private String dataAbertura;

}
