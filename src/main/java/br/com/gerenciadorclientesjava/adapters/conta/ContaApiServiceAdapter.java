package br.com.gerenciadorclientesjava.adapters.conta;

import br.com.gerenciadorclientesjava.apis.entities.ContaAPI;
import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import br.com.gerenciadorclientesjava.services.entities.Cliente;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.entities.Login;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContaApiServiceAdapter {

    private List<Conta> contasAPI;
    private Conta contaAPI;

    public ContaApiServiceAdapter(ContaAPI contaAPI) {
        this.contaAPI = convertContaApiEmConta(contaAPI);
    }



    public ContaApiServiceAdapter(List<ContaAPI> contas) {
        this.contasAPI = convertListAPIEmListConta(contas);
    }



    private List<Conta> convertListAPIEmListConta(List<ContaAPI> contaAPIS) {

        contasAPI = new ArrayList<>();
        for (ContaAPI contaAPI : contaAPIS) {
            contasAPI.add(convertContaApiEmConta(contaAPI));
        }
        return contasAPI;
    }

    private Conta convertContaApiEmConta(ContaAPI contaApi) {
        return Conta.builder()
                .numeroConta(contaApi.getNumeroConta())
                .tipoConta(contaApi.getTipoConta())
                .cliente(Cliente.builder()
                        .idCliente(contaApi.getClienteAPI().getIdCliente())
                        .tipoPessoa(contaApi.getClienteAPI().getTipoCliente())
                        .nome(contaApi.getClienteAPI().getCliente())
                        .data(contaApi.getClienteAPI().getData())
                        .documento(contaApi.getClienteAPI().getDocumento())
                        .rg(contaApi.getClienteAPI().getRg())
                        .serasa(contaApi.getClienteAPI().getSerasa())
                        .nomeDoPai(contaApi.getClienteAPI().getNomeDoPai())
                        .nomeDaMae(contaApi.getClienteAPI().getNomeDaMae())
                        .login(Login.builder()
                                .id(contaApi.getClienteAPI().getLogin().getId())
                                .senha(contaApi.getClienteAPI().getLogin().getSenha())
                                .build())
                        .build())
                .build();
    }
}
