package br.com.gerenciadorclientesjava.services.util;

import br.com.gerenciadorclientesjava.services.entities.enuns.TipoContaEnum;
import br.com.gerenciadorclientesjava.services.entities.enuns.TipoPessoaEnum;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;

public class ContaUtil {

    public static void validaPessoaEConta(String documento, Integer tipoPessoa, String tipoConta) throws ContaException {

        boolean check = false;

        if (documento.length() == 11 && tipoPessoa == TipoPessoaEnum.FISICA.ordinal() && (tipoConta.equals(String.valueOf(TipoContaEnum.CORRENTE.ordinal())) || tipoConta.equals(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))) || documento.length() == 14 && tipoPessoa == TipoPessoaEnum.JURIDICA.ordinal() && tipoConta.equals(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))) {
                check = true;
        }else {

            throw new ContaException("Pessoa ou Conta Incorreta para esse Documento");

        }

    }

}
