package br.com.gerenciadorclientesjava.adapters.conta;

import br.com.gerenciadorclientesjava.apis.entities.ContaFisicaAPI;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContaFisicaApiAdapter {
    private ContaFisicaAPI contaFisicaAPI;
    private List<ContaFisicaAPI> contasFisicasAPI;

    public ContaFisicaApiAdapter(Conta conta) {
        contaFisicaAPI = convertContaParaContaFisicaAPI(conta);
    }

    public ContaFisicaApiAdapter(List<Conta> conta) {
        contasFisicasAPI = convertListContaParaListContaFisicaAPI(conta);
    }

    private List<ContaFisicaAPI> convertListContaParaListContaFisicaAPI(List<Conta> contas) {
        List<ContaFisicaAPI> contasAPI = new ArrayList<>();
        for (Conta conta : contas) {
            contasAPI.add(convertContaParaContaFisicaAPI(conta));
        }
        return contasAPI;
    }

    private ContaFisicaAPI convertContaParaContaFisicaAPI(Conta conta) {
        return ContaFisicaAPI.builder()
                .numeroConta(conta.getNumeroConta())
                .tipoPessoa(conta.getTipoPessoa())
                .nomeCompleto(conta.getNome())
                .dataNascimento(conta.getData())
                .tipoConta(conta.getTipoConta())
                .cpf(conta.getDocumento())
                .rg(conta.getRg())
                .serasa(conta.getSerasa())
                .nomeDoPai(conta.getNomeDoPai())
                .nomeDaMae(conta.getNomeDaMae())
                .senha(conta.getSenha())
                .build();
    }
}