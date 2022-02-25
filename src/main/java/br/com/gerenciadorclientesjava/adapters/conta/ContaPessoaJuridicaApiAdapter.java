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
    private List<ContaPessoaJuridicaAPI> contasPessoaJuridicaAPI;

    public ContaPessoaJuridicaApiAdapter(Conta conta) {
        contaPessoaJuridicaApi = convertContaParaContaClientePessoaJuridicaApi(conta);
    }

    public ContaPessoaJuridicaApiAdapter(List<Conta> conta) {
        contasPessoaJuridicaAPI = convertListContaParaListContaClientePessoaJuridicaApi(conta);
    }

    private List<ContaPessoaJuridicaAPI> convertListContaParaListContaClientePessoaJuridicaApi(List<Conta> contas) {
        List<ContaPessoaJuridicaAPI> contasPessoaJuridicaAPI = new ArrayList<>();
        for (Conta conta : contas) {
            contasPessoaJuridicaAPI.add(convertContaParaContaClientePessoaJuridicaApi(conta));
        }
        return contasPessoaJuridicaAPI;
    }

    private ContaPessoaJuridicaAPI convertContaParaContaClientePessoaJuridicaApi(Conta conta) {
        return ContaPessoaJuridicaAPI.builder()
                .numeroConta(conta.getNumeroConta())
                .tipoConta(conta.getTipoConta())
                .clientePessoaJuridicaAPI(ClientePessoaJuridicaAPI.builder()
                        .tipoCliente(conta.getCliente().getTipoPessoa())
                        .cliente(conta.getCliente().getNome())
                        .dataAbertura(conta.getCliente().getData())
                        .cnpj(conta.getCliente().getDocumento())
                        .serasa(conta.getCliente().getSerasa())
                        .login(LoginAPI.builder()
                                .senha(conta.getCliente().getLogin().getSenha())
                                .build())
                        .build())
                .build();
    }
}
