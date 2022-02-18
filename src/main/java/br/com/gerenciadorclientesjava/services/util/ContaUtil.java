package br.com.gerenciadorclientesjava.services.util;

import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.entities.enuns.TipoContaEnum;
import br.com.gerenciadorclientesjava.services.entities.enuns.TipoPessoaEnum;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ContaUtil {

    private static ContaService contaService;

    @Autowired
    public ContaUtil(ContaService contaService) {
        this.contaService = contaService;
    }

    public static void validaPessoaEConta(String documento, Integer tipoPessoa, Integer tipoConta) throws ContaException {

        boolean check = false;

        if (documento.length() == 11 && tipoPessoa == TipoPessoaEnum.FISICA.ordinal() && (tipoConta == TipoContaEnum.CORRENTE.ordinal() || tipoConta == TipoContaEnum.POUPANCA.ordinal()) || (documento.length() == 14 && tipoPessoa == TipoPessoaEnum.JURIDICA.ordinal() && tipoConta == TipoContaEnum.CORRENTE.ordinal())) {
                check = true;
        }else {

            throw new ContaException("Pessoa ou Conta Incorreta para esse Documento");

        }


    }

}
