package br.com.gerenciadorclientesjava.adapters.conta;

import br.com.gerenciadorclientesjava.apis.entities.*;
import br.com.gerenciadorclientesjava.apis.entities.ContaPessoaJuridicaAPI;
import br.com.gerenciadorclientesjava.services.entities.Conta;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContaPessoaJuridicaApiAdapter {

    private ContaPessoaJuridicaAPI contaPessoaJuridicaApi;

    public ContaPessoaJuridicaApiAdapter(Conta conta) {
        contaPessoaJuridicaApi = convertContaParaContaClientePessoaJuridicaApi(conta);
    }

    private ContaPessoaJuridicaAPI convertContaParaContaClientePessoaJuridicaApi(Conta conta) {
        return ContaPessoaJuridicaAPI.builder()
                .numeroConta(conta.getNumeroConta())
                .tipoConta(conta.getTipoConta())
                .clientePessoaJuridicaAPI(ClientePessoaJuridicaAPI.builder()
                        .idCliente(conta.getCliente().getIdCliente())
                        .tipoCliente(conta.getCliente().getTipoPessoa())
                        .cliente(conta.getCliente().getNome())
                        .dataAbertura(conta.getCliente().getData())
                        .cnpj(conta.getCliente().getDocumento())
                        .serasa(conta.getCliente().getSerasa())
                        .login(LoginAPI.builder()
                                .id(conta.getCliente().getLogin().getId())
                                .senha(conta.getCliente().getLogin().getSenha())
                                .build())
                        .build())
                .build();
    }
}
