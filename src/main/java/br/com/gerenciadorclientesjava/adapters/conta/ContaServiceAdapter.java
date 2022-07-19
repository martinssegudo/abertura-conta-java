package br.com.gerenciadorclientesjava.adapters.conta;

import br.com.gerenciadorclientesjava.apis.entities.ContaAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaPessoaFisicaAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaPessoaJuridicaAPI;
import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import br.com.gerenciadorclientesjava.services.entities.Cliente;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.entities.Login;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContaServiceAdapter {

    private Conta conta;
    private List<Conta> contasEntity;


    public ContaServiceAdapter(ContaPessoaJuridicaAPI contaAPI) {
        this.conta = convertContaPessoaJuridicaApiEmConta(contaAPI);
    }

    public ContaServiceAdapter(ContaPessoaFisicaAPI contaAPI) {
        this.conta = convertContaPessoaFisicaApiEmConta(contaAPI);
    }

    public ContaServiceAdapter(ContaEntity contaEntity) {
        setConta(convertContaEntityEmConta(contaEntity));
    }

    public ContaServiceAdapter(List<ContaEntity> contas) {
        this.contasEntity = convertListEntityEmListConta(contas);
    }


    private List<Conta> convertListEntityEmListConta(List<ContaEntity> contaEntityList) {
        contasEntity = new ArrayList<>();
        for (ContaEntity contaEntity : contaEntityList) {
            contasEntity.add(convertContaEntityEmConta(contaEntity));
        }
        return contasEntity;
    }



    private Conta convertContaPessoaFisicaApiEmConta(ContaPessoaFisicaAPI contaApi) {
        return Conta.builder()
                .numeroConta(contaApi.getNumeroConta())
                .tipoConta(contaApi.getTipoConta())
                .cliente(Cliente.builder()
                        .idCliente(contaApi.getClientePessoaFisicaAPI().getIdCliente())
                        .tipoPessoa(contaApi.getClientePessoaFisicaAPI().getTipoCliente())
                        .nome(contaApi.getClientePessoaFisicaAPI().getCliente())
                        .data(contaApi.getClientePessoaFisicaAPI().getDataNascimento())
                        .documento(contaApi.getClientePessoaFisicaAPI().getCpf())
                        .rg(contaApi.getClientePessoaFisicaAPI().getRg())
                        .serasa(contaApi.getClientePessoaFisicaAPI().getSerasa())
                        .nomeDoPai(contaApi.getClientePessoaFisicaAPI().getNomeDoPai())
                        .nomeDaMae(contaApi.getClientePessoaFisicaAPI().getNomeDaMae())
                        .login(Login.builder()
                                .id(contaApi.getClientePessoaFisicaAPI().getLogin().getId())
                                .senha(contaApi.getClientePessoaFisicaAPI().getLogin().getSenha())
                                .build())
                        .build())
                .build();
    }

    private Conta convertContaPessoaJuridicaApiEmConta(ContaPessoaJuridicaAPI contaApi) {
        return Conta.builder()
                .numeroConta(contaApi.getNumeroConta())
                .tipoConta(contaApi.getTipoConta())
                .cliente(Cliente.builder()
                        .idCliente(contaApi.getClientePessoaJuridicaAPI().getIdCliente())
                        .tipoPessoa(contaApi.getClientePessoaJuridicaAPI().getTipoCliente())
                        .nome(contaApi.getClientePessoaJuridicaAPI().getCliente())
                        .data(contaApi.getClientePessoaJuridicaAPI().getDataAbertura())
                        .documento(contaApi.getClientePessoaJuridicaAPI().getCnpj())
                        .serasa(contaApi.getClientePessoaJuridicaAPI().getSerasa())
                        .login(Login.builder()
                                .id(contaApi.getClientePessoaJuridicaAPI().getLogin().getId())
                                .senha(contaApi.getClientePessoaJuridicaAPI().getLogin().getSenha())
                                .build())
                        .build())
                .build();
    }

    private Conta convertContaEntityEmConta(ContaEntity contaEntity) {
        return Conta.builder()
                .numeroConta(contaEntity.getNumeroConta())
                .tipoConta(contaEntity.getTipoConta())
                .cliente(Cliente.builder()
                        .idCliente(contaEntity.getClienteEntity().getIdCliente())
                        .tipoPessoa(contaEntity.getClienteEntity().getTipoCliente())
                        .nome(contaEntity.getClienteEntity().getCliente())
                        .data(contaEntity.getClienteEntity().getData())
                        .documento(contaEntity.getClienteEntity().getDocumento())
                        .rg(contaEntity.getClienteEntity().getRg())
                        .serasa(contaEntity.getClienteEntity().getSerasa())
                        .nomeDaMae(contaEntity.getClienteEntity().getNomeDaMae())
                        .nomeDoPai(contaEntity.getClienteEntity().getNomeDoPai())
                        .login(Login.builder()
                                .id(contaEntity.getClienteEntity().getLoginEntity().getId())
                                .senha(contaEntity.getClienteEntity().getLoginEntity().getSenha())
                                .build())
                        .build())
                .build();
    }
}