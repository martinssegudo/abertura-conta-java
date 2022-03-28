package br.com.gerenciadorclientesjava.apis.entities;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientePessoaFisicaAPI {

    private Long idCliente;

    @NotNull
    @Size(max = 1)
    private Integer tipoCliente;

    @NotNull
    @Size(min = 10, max = 200)
    private String cliente;

    @NotNull
    @Size(min = 11, max = 11)
    private String cpf;

    @NotNull
    @Size(min = 9, max = 9)
    private String rg;

    @Max(1000)
    @Size(min = 1, max = 4)
    private Integer serasa;

    @Size(min = 10, max = 200)
    private String nomeDoPai;

    @NotNull
    @Size(min = 10, max = 200)
    private String nomeDaMae;

    private LoginAPI login;

    @NotNull
    @Size(min = 10, max = 10)
    private String dataNascimento;

}
