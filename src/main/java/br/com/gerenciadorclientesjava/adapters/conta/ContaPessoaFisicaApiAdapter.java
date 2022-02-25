package br.com.gerenciadorclientesjava.adapters.conta;

import br.com.gerenciadorclientesjava.apis.entities.*;
import br.com.gerenciadorclientesjava.services.entities.Cliente;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.entities.Login;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContaPessoaFisicaApiAdapter {

    private ContaPessoaFisicaAPI contaPessoaFisicaApi;
    private List<ContaPessoaFisicaAPI> contasPessoaFisicaApi;

    public ContaPessoaFisicaApiAdapter(Conta conta) {
        contaPessoaFisicaApi = convertContaParaContaClientePessoaFisicaApi(conta);
    }

    public ContaPessoaFisicaApiAdapter(List<Conta> conta) {
        contasPessoaFisicaApi = convertListContaParaListContaClientePessoaFisicaApi(conta);
    }

    private List<ContaPessoaFisicaAPI> convertListContaParaListContaClientePessoaFisicaApi(List<Conta> contas) {
        List<ContaPessoaFisicaAPI> contasPessoaFisicaApi = new ArrayList<>();
        for (Conta conta : contas) {
            contasPessoaFisicaApi.add(convertContaParaContaClientePessoaFisicaApi(conta));
        }
        return contasPessoaFisicaApi;
    }

    private ContaPessoaFisicaAPI convertContaParaContaClientePessoaFisicaApi(Conta conta) {
        return ContaPessoaFisicaAPI.builder()
                .numeroConta(conta.getNumeroConta())
                .tipoConta(conta.getTipoConta())
                .clientePessoaFisicaAPI(ClientePessoaFisicaAPI.builder()
                        .tipoCliente(conta.getCliente().getTipoPessoa())
                        .cliente(conta.getCliente().getNome())
                        .dataNascimento(conta.getCliente().getData())
                        .cpf(conta.getCliente().getDocumento())
                        .rg(conta.getCliente().getRg())
                        .nomeDaMae(conta.getCliente().getNomeDaMae())
                        .nomeDoPai(conta.getCliente().getNomeDoPai())
                        .serasa(conta.getCliente().getSerasa())
                        .login(LoginAPI.builder()
                                .senha(conta.getCliente().getLogin().getSenha())
                                .build())
                        .build())
                .build();
    }
}