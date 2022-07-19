package br.com.gerenciadorclientesjava.adapters.conta;

import br.com.gerenciadorclientesjava.apis.entities.*;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContaPessoaFisicaApiAdapter {

    private ContaPessoaFisicaAPI contaPessoaFisicaApi;
    private List<ContaPessoaFisicaAPI> contasPessoaFisicaApi = new ArrayList<>();

    public ContaPessoaFisicaApiAdapter(Conta conta) {
        contaPessoaFisicaApi = convertContaParaContaClientePessoaFisicaApi(conta);
    }

    private ContaPessoaFisicaAPI convertContaParaContaClientePessoaFisicaApi(Conta conta) {
        return ContaPessoaFisicaAPI.builder()
                .numeroConta(conta.getNumeroConta())
                .tipoConta(conta.getTipoConta())
                .clientePessoaFisicaAPI(ClientePessoaFisicaAPI.builder()
                        .idCliente(conta.getCliente().getIdCliente())
                        .tipoCliente(conta.getCliente().getTipoPessoa())
                        .cliente(conta.getCliente().getNome())
                        .dataNascimento(conta.getCliente().getData())
                        .cpf(conta.getCliente().getDocumento())
                        .rg(conta.getCliente().getRg())
                        .nomeDaMae(conta.getCliente().getNomeDaMae())
                        .nomeDoPai(conta.getCliente().getNomeDoPai())
                        .serasa(conta.getCliente().getSerasa())
                        .login(LoginAPI.builder()
                                .id(conta.getCliente().getLogin().getId())
                                .senha(conta.getCliente().getLogin().getSenha())
                                .build())
                        .build())
                .build();
    }
}