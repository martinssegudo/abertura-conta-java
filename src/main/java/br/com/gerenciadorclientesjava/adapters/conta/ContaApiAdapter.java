package br.com.gerenciadorclientesjava.adapters.conta;

import br.com.gerenciadorclientesjava.apis.entities.ContaAPI;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContaApiAdapter {

    private ContaAPI contaAPI;
    private List<ContaAPI> contasAPI;

    public ContaApiAdapter(Conta conta) {
        contaAPI = convertContaParaContaAPI(conta);
    }

    public ContaApiAdapter(List<Conta> conta) {
        contasAPI = convertListContaParaListContaAPI(conta);
    }

    private List<ContaAPI> convertListContaParaListContaAPI(List<Conta> contas) {
        List<ContaAPI> contasAPI = new ArrayList<>();
        for (Conta conta : contas) {
            contasAPI.add(convertContaParaContaAPI(conta));
        }
        return contasAPI;
    }

    private ContaAPI convertContaParaContaAPI(Conta conta) {
        return ContaAPI.builder()
                .numeroConta(conta.getNumeroConta())
                .tipoPessoa(conta.getTipoPessoa())
                .nome(conta.getNome())
                .data(conta.getData())
                .senha(conta.getSenha())
                .tipoConta(conta.getTipoConta())
                .documento(conta.getDocumento())
                .serasa(conta.getSerasa())
                .erro(conta.getErro())
                .build();
    }
}