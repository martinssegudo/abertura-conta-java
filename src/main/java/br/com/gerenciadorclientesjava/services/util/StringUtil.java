package br.com.gerenciadorclientesjava.services.util;

import br.com.gerenciadorclientesjava.services.entities.enuns.TipoPessoaEnum;
import br.com.gerenciadorclientesjava.services.exceptions.*;

public class StringUtil {

    public static void validaSenha(String senha) throws SenhaInvalidaException, SenhaMenorSeisCaracteresException {

            if (senha.length() >= 6) {

                String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}";
                senha.matches(pattern);

                if(!senha.matches(pattern)){
                    throw new SenhaInvalidaException();
                }

            } else {
                throw new SenhaMenorSeisCaracteresException();
            }
    }


    public static void validaRg(Integer tipoPessoa , String rg) throws RgDiferenteNoveCaracteresException, RgInvalidoException, RgNuloException {

        boolean check = false;

        if(tipoPessoa == TipoPessoaEnum.FISICA.ordinal() && rg!= null) {
            if (rg.length() == 9) {

                String pattern = "(?=.*[0-9])(?=\\S+$).{9}";
                rg.matches(pattern);
                check = true;

                if (!rg.matches(pattern)) {

                    throw new RgInvalidoException();
                }

            } else {

                throw new RgDiferenteNoveCaracteresException();
            }

        }else if(tipoPessoa == TipoPessoaEnum.JURIDICA.ordinal() && rg == null){
            check = true;
        }else{
            throw new RgNuloException();
        }
    }

    public static void validaDocumento(String documento) throws CPFException, CNPJException, DocumentoException, DocumentoNuloException {
        
        boolean check = false;
        if(documento != null) {
            if (documento.length() == 11) {

                String pattern = "(?=.*[0-9])(?=\\S+$).{11}";
                documento.matches(pattern);
                check = true;

                if (!documento.matches(pattern)) {
                    throw new CPFException();
                }

            } else if (documento.length() == 14) {

                String pattern = "(?=.*[0-9])(?=\\S+$).{14}";
                documento.matches(pattern);
                check = true;

                if (!documento.matches(pattern)) {

                    throw new CNPJException();

                }

            } else {

                throw new DocumentoException();

            }
        }else{

            throw new DocumentoNuloException();

        }

    }

    public static void validaNomeComDezCaracteres(Integer tipoPessoa, String nome) throws NomeException {
        boolean check = false;
        if((tipoPessoa == TipoPessoaEnum.FISICA.ordinal() && nome.length() >= 10) || tipoPessoa == TipoPessoaEnum.JURIDICA.ordinal()){
            check = true;
        }else{
            throw new NomeException();
        }
    }
}
