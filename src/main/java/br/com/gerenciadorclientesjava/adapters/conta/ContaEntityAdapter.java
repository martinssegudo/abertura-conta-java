package br.com.gerenciadorclientesjava.adapters.conta;

import br.com.gerenciadorclientesjava.db.entities.ClienteEntity;
import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import br.com.gerenciadorclientesjava.db.entities.LoginEntity;
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
                .tipoConta(conta.getTipoConta())
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(conta.getCliente().getTipoPessoa())
                        .cliente(conta.getCliente().getNome())
                        .data(conta.getCliente().getData())
                        .documento(conta.getCliente().getDocumento())
                        .rg(conta.getCliente().getRg())
                        .serasa(conta.getCliente().getSerasa())
                        .nomeDaMae(conta.getCliente().getNomeDaMae())
                        .nomeDoPai(conta.getCliente().getNomeDoPai())
                        .loginEntity(LoginEntity.builder()
                                .senha(conta.getCliente().getLogin().getSenha())
                                .build())
                        .build())
                .build();
    }
}
