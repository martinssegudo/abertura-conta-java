package br.com.gerenciadorclientesjava.adapters.conta;

import br.com.gerenciadorclientesjava.apis.entities.ContaJuridicaAPI;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContaJuridicaApiAdapter {
    private ContaJuridicaAPI contaJuridicaAPI;
    private List<ContaJuridicaAPI> contasJuridicasAPI;

    public ContaJuridicaApiAdapter(Conta conta) {
        contaJuridicaAPI = convertContaParaContaJuridicaAPI(conta);
    }

    public ContaJuridicaApiAdapter(List<Conta> conta) {
        contasJuridicasAPI = convertListContaParaListContaAPI(conta);
    }

    private List<ContaJuridicaAPI> convertListContaParaListContaAPI(List<Conta> contas) {
        List<ContaJuridicaAPI> contasAPI = new ArrayList<>();
        for (Conta conta : contas) {
            contasAPI.add(convertContaParaContaJuridicaAPI(conta));
        }
        return contasAPI;
    }

    private ContaJuridicaAPI convertContaParaContaJuridicaAPI(Conta conta) {
        return ContaJuridicaAPI.builder()
                .numeroConta(conta.getNumeroConta())
                .tipoPessoa(conta.getTipoPessoa())
                .razaoSocial(conta.getNome())
                .dataAbertura(conta.getData())
                .tipoConta(conta.getTipoConta())
                .cnpj(conta.getDocumento())
                .serasa(conta.getSerasa())
                .senha(conta.getSenha())
                .build();
    }
}


