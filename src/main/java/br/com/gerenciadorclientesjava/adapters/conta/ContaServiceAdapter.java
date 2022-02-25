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
    private List<Conta> contas;


    public ContaServiceAdapter(ContaAPI contaAPI) {
        this.conta = convertContaApiEmConta(contaAPI);
    }
    public ContaServiceAdapter(ContaPessoaJuridicaAPI contaAPI) {
        this.conta = convertContaPessoaJuridicaApiEmConta(contaAPI);
    }
    public ContaServiceAdapter(ContaPessoaFisicaAPI contaAPI) {
        this.conta = convertContaPessoaFisicaApiEmConta(contaAPI);
    }


    public ContaServiceAdapter(ContaEntity contaEntity) {
        setConta(convertContaEntityEmConta(contaEntity));
    }

    public ContaServiceAdapter(List<ContaEntity> contaEntities) {
        setContas(convertListEntityEmListConta(contaEntities));
    }

    private List<Conta> convertListEntityEmListConta(List<ContaEntity> contaEntityList) {
        contas = new ArrayList<>();
        for (ContaEntity contaEntity : contaEntityList) {
            contas.add(convertContaEntityEmConta(contaEntity));
        }
        return contas;
    }

    private Conta convertContaApiEmConta(ContaAPI contaApi) {
        return Conta.builder()
                .numeroConta(contaApi.getNumeroConta())
                .tipoConta(contaApi.getTipoConta())
                .cliente(Cliente.builder()
                        .tipoPessoa(contaApi.getClienteAPI().getTipoCliente())
                        .nome(contaApi.getClienteAPI().getCliente())
                        .data(contaApi.getClienteAPI().getData())
                        .documento(contaApi.getClienteAPI().getDocumento())
                        .rg(contaApi.getClienteAPI().getRg())
                        .serasa(contaApi.getClienteAPI().getSerasa())
                        .nomeDoPai(contaApi.getClienteAPI().getNomeDoPai())
                        .nomeDaMae(contaApi.getClienteAPI().getNomeDaMae())
                        .login(Login.builder()
                                .senha(contaApi.getClienteAPI().getLogin().getSenha())
                                .build())
                        .build())
                .build();
    }

    private Conta convertContaPessoaFisicaApiEmConta(ContaPessoaFisicaAPI contaApi) {
        return Conta.builder()
                .numeroConta(contaApi.getNumeroConta())
                .tipoConta(contaApi.getTipoConta())
                .cliente(Cliente.builder()
                        .tipoPessoa(contaApi.getClientePessoaFisicaAPI().getTipoCliente())
                        .nome(contaApi.getClientePessoaFisicaAPI().getCliente())
                        .data(contaApi.getClientePessoaFisicaAPI().getDataNascimento())
                        .documento(contaApi.getClientePessoaFisicaAPI().getCpf())
                        .rg(contaApi.getClientePessoaFisicaAPI().getRg())
                        .serasa(contaApi.getClientePessoaFisicaAPI().getSerasa())
                        .nomeDoPai(contaApi.getClientePessoaFisicaAPI().getNomeDoPai())
                        .nomeDaMae(contaApi.getClientePessoaFisicaAPI().getNomeDaMae())
                        .login(Login.builder()
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
                        .tipoPessoa(contaApi.getClientePessoaJuridicaAPI().getTipoCliente())
                        .nome(contaApi.getClientePessoaJuridicaAPI().getCliente())
                        .data(contaApi.getClientePessoaJuridicaAPI().getDataAbertura())
                        .documento(contaApi.getClientePessoaJuridicaAPI().getCnpj())
                        .serasa(contaApi.getClientePessoaJuridicaAPI().getSerasa())
                        .login(Login.builder()
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
                        .tipoPessoa(contaEntity.getClienteEntity().getTipoCliente())
                        .nome(contaEntity.getClienteEntity().getCliente())
                        .data(contaEntity.getClienteEntity().getData())
                        .documento(contaEntity.getClienteEntity().getDocumento())
                        .rg(contaEntity.getClienteEntity().getRg())
                        .serasa(contaEntity.getClienteEntity().getSerasa())
                        .nomeDaMae(contaEntity.getClienteEntity().getNomeDaMae())
                        .nomeDoPai(contaEntity.getClienteEntity().getNomeDoPai())
                        .login(Login.builder()
                                .senha(contaEntity.getClienteEntity().getLoginEntity().getSenha())
                                .build())
                        .build())
                .build();
    }
}