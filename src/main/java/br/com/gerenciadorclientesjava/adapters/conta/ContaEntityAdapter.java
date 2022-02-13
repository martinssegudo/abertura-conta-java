package br.com.gerenciadorclientesjava.adapters.conta;

import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import lombok.Data;

@Data
public class ContaEntityAdapter {

    private ContaEntity contaEntity;

    public ContaEntityAdapter(Conta conta) {
       this.contaEntity = converteContaEmContaEntity(conta);
    }

    private ContaEntity converteContaEmContaEntity(Conta conta) {
        return   ContaEntity.builder()
                .numeroConta(conta.getNumeroConta())
                .tipoPessoa(conta.getTipoPessoa())
                .nome(conta.getNome())
                .data(conta.getData())
                .tipoConta(conta.getTipoConta())
                .documento(conta.getDocumento())
                .rg(conta.getRg())
                .serasa(conta.getSerasa())
                .nomeDoPai(conta.getNomeDoPai())
                .nomeDaMae(conta.getNomeDaMae())
                .senha(conta.getSenha())
                .build();
    }
}
