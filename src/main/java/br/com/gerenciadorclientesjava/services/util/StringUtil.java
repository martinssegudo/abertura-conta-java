package br.com.gerenciadorclientesjava.services.util;

import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.entities.enuns.TipoContaEnum;
import br.com.gerenciadorclientesjava.services.entities.enuns.TipoPessoaEnum;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class StringUtil {


    public static void validaSenha(Conta conta) throws ContaException {

        if(conta.getSenha().length() >= 6) {
            String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}";
            conta.getSenha().matches(pattern);
        }

        throw new ContaException("Senha Invalida ou menor que 6 caracteres");
    }


    /*public static Conta validaConta(Conta conta) throws ContaException{
        double anos = DataUtil.validaNascimento(conta.getData());
        if (validaSenha(conta)) {
            if (conta.getTipoPessoa().equals(TipoPessoaEnum.JURIDICA.ordinal()) && conta.getTipoConta().equals(TipoContaEnum.CORRENTE.ordinal())) {
                conta = ContaUtil.criarJuridica(conta);

            } else if (conta.getTipoPessoa().equals(TipoPessoaEnum.FISICA.ordinal()) && anos >= 18 && (conta.getTipoConta().equals(TipoContaEnum.CORRENTE.ordinal()) || conta.getTipoConta().equals(TipoContaEnum.POUPANCA.ordinal()))) {
                if(conta.getNome().length() > 10) {
                    conta = ContaUtil.criarFisica(conta);

                }else{
                    throw new ContaException("Nome precisa ter mais de 10 caracteres");
                }
            } else {
                throw new ContaException("Um tipo de pessoa ou conta nao existente foi selecionado ou idade menor que 18");
            }
        } else {
            throw new ContaException("Senha informada deve conter no minimo 6 caracteres e pelo menos 1 caractere maiusculo e um caractere especial");
        }

        return conta;
    }
     */

    public static void validaPessoaEConta(Integer tipoPessoa, Integer tipoConta) throws ContaException {
        boolean check = false;
        if((tipoPessoa == TipoPessoaEnum.FISICA.ordinal() && (tipoConta == TipoContaEnum.POUPANCA.ordinal() || tipoConta == TipoContaEnum.CORRENTE.ordinal())) || tipoPessoa == TipoPessoaEnum.JURIDICA.ordinal() && tipoConta == TipoContaEnum.CORRENTE.ordinal()){
            check = true;
        }
        
        throw new ContaException("Tipo de Conta ou Pessoa errada");
        
    }

    public static void validaDocumento(String documento) throws ContaException {
        
        boolean check = false;
        if(!documento.isEmpty() || !documento.isBlank()){
            check = true;
        }
        
        throw new ContaException("Documento não deve ser nulo ou vazio");
    }
}
