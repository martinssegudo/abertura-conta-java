package br.com.gerenciadorclientesjava.services.util;

import br.com.gerenciadorclientesjava.services.entities.enuns.TipoPessoaEnum;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataUtil {

    public static void validaNascimento(Integer tipoPessoa, String dataString) throws ContaException, ParseException {

        SimpleDateFormat formato;
        Date data;

        try {

            formato = new SimpleDateFormat("dd/MM/yyyy");
            formato.setLenient(false);
            data = formato.parse(dataString);
            boolean check = false;
            if(tipoPessoa == TipoPessoaEnum.FISICA.ordinal()) {
                Date secondDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
                long diff = secondDate.getTime() - data.getTime();
                TimeUnit time = TimeUnit.DAYS;
                long difference = time.convert(diff, TimeUnit.MILLISECONDS);
                double anos = difference / 365f;

                if (anos >= 18) {
                    check = true;
                } else {
                    throw new ContaException("Idade Menor que 18 anos");
                }
            }

        }catch(ParseException e) {

            throw new ContaException("Data Invalida");

        }
    }
}
