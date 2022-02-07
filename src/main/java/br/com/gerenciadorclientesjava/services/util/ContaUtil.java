package br.com.gerenciadorclientesjava.services.util;

import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.entities.enuns.TipoPessoaEnum;

public class ContaUtil {

    private ContaUtil() {
    }


    public static Conta criarJuridica(Conta conta) {
        return Conta.builder()
                .numeroConta(conta.getNumeroConta())
                .nome(conta.getNome())
                .data(conta.getData())
                .documento(conta.getDocumento())
                .tipoConta(conta.getTipoConta())
                .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                .serasa(conta.getSerasa())
                .senha(conta.getSenha())
                .build();
    }

    public static Conta criarFisica(Conta conta) {
        //System.out.println("CRIOU UM SITH");

        return Conta.builder()
                .numeroConta(conta.getNumeroConta())
                .nome(conta.getNome())
                .data(conta.getData())
                .documento(conta.getDocumento())
                .rg(conta.getRg())
                .tipoConta(conta.getTipoConta())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .serasa(conta.getSerasa())
                .nomeDoPai(conta.getNomeDoPai())
                .nomeDaMae(conta.getNomeDaMae())
                .senha(conta.getSenha())
                .build();
    }
}
