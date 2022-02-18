package br.com.gerenciadorclientesjava.services.util;

import br.com.gerenciadorclientesjava.services.entities.enuns.TipoPessoaEnum;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;

public class StringUtil {

    public static void validaSenha(String senha) throws ContaException {


            if (senha.length() >= 6) {

                String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}";
                senha.matches(pattern);

                if(!senha.matches(pattern)){
                    throw new ContaException("Senha Invalida");
                }

            } else {
                throw new ContaException("Senha menor que 6 caracteres");
            }
    }


    public static void validaRg(Integer tipoPessoa , String rg) throws ContaException {

        boolean check = false;


        if(tipoPessoa == TipoPessoaEnum.FISICA.ordinal() && rg!= null) {
            if (rg.length() == 9) {

                String pattern = "(?=.*[0-9])(?=\\S+$).{9}";
                rg.matches(pattern);
                check = true;

                if (!rg.matches(pattern)) {
                    throw new ContaException("RG Invalido");
                }


            } else {
                throw new ContaException("RG tem que ter 9 caracteres");
            }
        }else if(tipoPessoa == TipoPessoaEnum.JURIDICA.ordinal() && rg == null){
            check = true;
        }else{
            throw new ContaException("RG não pode ser nulo");
        }
    }

    public static void validaDocumento(String documento) throws ContaException {
        
        boolean check = false;
        if(documento != null) {
            if (documento.length() == 11) {

                String pattern = "(?=.*[0-9])(?=\\S+$).{11}";
                documento.matches(pattern);
                check = true;

                if (!documento.matches(pattern)) {
                    throw new ContaException("CPF Invalido");
                }

            } else if (documento.length() == 14) {

                String pattern = "(?=.*[0-9])(?=\\S+$).{14}";
                documento.matches(pattern);
                check = true;

                if (!documento.matches(pattern)) {

                    throw new ContaException("CNPJ Invalido");

                }

            } else {

                throw new ContaException("Documento deve ter 11 ou 14 digitos");

            }
        }else{

            throw new ContaException("Documento não pode ser nulo");

        }

    }

    public static void validaNomeComDezCaracteres(Integer tipoPessoa, String nome) throws ContaException {
        boolean check = false;
        if((tipoPessoa == TipoPessoaEnum.FISICA.ordinal() && nome.length() >= 10) || tipoPessoa == TipoPessoaEnum.JURIDICA.ordinal()){
            check = true;
        }else{
            throw new ContaException("Nomes Proprios precisam ter mais de 10 caracteres");
        }
    }
}
