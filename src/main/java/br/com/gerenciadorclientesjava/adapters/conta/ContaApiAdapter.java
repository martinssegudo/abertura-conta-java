package br.com.gerenciadorclientesjava.adapters.conta;

import br.com.gerenciadorclientesjava.apis.entities.ClienteAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaAPI;
import br.com.gerenciadorclientesjava.apis.entities.LoginAPI;
import br.com.gerenciadorclientesjava.services.entities.Cliente;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.entities.Login;
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
                .tipoConta(conta.getTipoConta())
                .clienteAPI(ClienteAPI.builder()
                        .tipoCliente(conta.getCliente().getTipoPessoa())
                        .cliente(conta.getCliente().getNome())
                        .data(conta.getCliente().getData())
                        .documento(conta.getCliente().getDocumento())
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