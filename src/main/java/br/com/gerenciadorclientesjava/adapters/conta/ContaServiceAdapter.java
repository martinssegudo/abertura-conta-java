package br.com.gerenciadorclientesjava.adapters.conta;

import br.com.gerenciadorclientesjava.apis.entities.ContaAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaFisicaAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaJuridicaAPI;
import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ContaServiceAdapter {

    private Conta conta;
    private List<Conta> contas;

    public ContaServiceAdapter(ContaJuridicaAPI contaJuridicaAPI) {
        this.conta = convertContaJuridicaApiEmConta(contaJuridicaAPI);
    }

    public ContaServiceAdapter(ContaFisicaAPI contaFisicaAPI) {
        this.conta = convertContaFisicaApiEmConta(contaFisicaAPI);
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
                .tipoPessoa(contaApi.getTipoPessoa())
                .nome(contaApi.getNome())
                .data(contaApi.getData())
                .tipoConta(contaApi.getTipoConta())
                .documento(contaApi.getDocumento())
                .serasa(contaApi.getSerasa())
                .build();
    }

    private Conta convertContaFisicaApiEmConta(ContaFisicaAPI contaFisicaApi) {
        return Conta.builder()
                .numeroConta(contaFisicaApi.getNumeroConta())
                .tipoPessoa(contaFisicaApi.getTipoPessoa())
                .nome(contaFisicaApi.getNomeCompleto())
                .data(contaFisicaApi.getDataNascimento())
                .tipoConta(contaFisicaApi.getTipoConta())
                .documento(contaFisicaApi.getCpf())
                .rg(contaFisicaApi.getRg())
                .serasa(contaFisicaApi.getSerasa())
                .nomeDoPai(contaFisicaApi.getNomeDoPai())
                .nomeDaMae(contaFisicaApi.getNomeDaMae())
                .senha(contaFisicaApi.getSenha())
                .build();
    }

    private Conta convertContaJuridicaApiEmConta(ContaJuridicaAPI contaJuridicaApi) {
        return Conta.builder()
                .numeroConta(contaJuridicaApi.getNumeroConta())
                .tipoPessoa(contaJuridicaApi.getTipoPessoa())
                .nome(contaJuridicaApi.getRazaoSocial())
                .data(contaJuridicaApi.getDataAbertura())
                .tipoConta(contaJuridicaApi.getTipoConta())
                .documento(contaJuridicaApi.getCnpj())
                .serasa(contaJuridicaApi.getSerasa())
                .senha(contaJuridicaApi.getSenha())
                .build();
    }

    private Conta convertContaEntityEmConta(ContaEntity contaEntity) {
        return Conta.builder()
                .numeroConta(contaEntity.getNumeroConta())
                .tipoPessoa(contaEntity.getTipoPessoa())
                .nome(contaEntity.getNome())
                .data(contaEntity.getData())
                .tipoConta(contaEntity.getTipoConta())
                .documento(contaEntity.getDocumento())
                .rg(contaEntity.getRg())
                .serasa(contaEntity.getSerasa())
                .nomeDoPai(contaEntity.getNomeDoPai())
                .nomeDaMae(contaEntity.getNomeDaMae())
                .senha(contaEntity.getSenha())
                .build();
    }
}