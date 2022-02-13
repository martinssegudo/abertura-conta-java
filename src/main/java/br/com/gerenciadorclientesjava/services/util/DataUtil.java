package br.com.gerenciadorclientesjava.services.util;

import br.com.gerenciadorclientesjava.services.entities.enuns.TipoPessoaEnum;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataUtil {

    public static void validaNascimento(Integer tipoPessoa, Date data) throws ContaException {

        boolean check = false;
        if(tipoPessoa == TipoPessoaEnum.FISICA.ordinal()){
            Date secondDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

            long diff = secondDate.getTime() - data.getTime();
            TimeUnit time = TimeUnit.DAYS;
            long difference = time.convert(diff, TimeUnit.MILLISECONDS);
            double anos = difference / 365;
            if (anos >= 18 || tipoPessoa == TipoPessoaEnum.JURIDICA.ordinal()){
                check = true;
            }else{
                throw new ContaException("Idade Menor que 18 anos");
            }
        }
    }
}
