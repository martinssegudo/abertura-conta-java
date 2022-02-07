package br.com.gerenciadorclientesjava.services.contracts.impl;

import br.com.gerenciadorclientesjava.adapters.conta.ContaEntityAdapter;
import br.com.gerenciadorclientesjava.adapters.conta.ContaServiceAdapter;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioContaEntity;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.entities.enuns.TipoContaEnum;
import br.com.gerenciadorclientesjava.services.entities.enuns.TipoPessoaEnum;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;
import br.com.gerenciadorclientesjava.services.util.ContaUtil;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ContaServiceImpl implements ContaService {

    private final RepositorioContaEntity contaRepositorio;
    private static ContaService instance;

    private ContaServiceImpl(RepositorioContaEntity contaRepositorio){
        this.contaRepositorio = contaRepositorio;
    }

    public static ContaService getInstance(RepositorioContaEntity contaRepositorio){
        if(instance == null){
            instance = new ContaServiceImpl(contaRepositorio);
        }

        return instance;
    }

    @Override
    public boolean salvarConta(Conta conta) throws ContaException {
        //System.out.print(TipoPessoaEnum.FISICA.ordinal());
           conta = validaConta(conta);

        contaRepositorio.save(new ContaEntityAdapter(conta).getContaEntity());

        return conta.getNumeroConta() != null;
    }

    @Override
    public List<Conta> listarTodos() {
        return new ContaServiceAdapter(contaRepositorio.findAll()).getContas();
    }

    @Override
    public Conta buscaPorId(Long id) {
        return new ContaServiceAdapter(contaRepositorio.findById(id).get()).getConta();
    }

    private boolean validaSenha(Conta conta){

        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}";
            return conta.getSenha().matches(pattern);
    }

    private Double validaData(Date data){

        Date secondDate = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());

        long diff = secondDate.getTime() - data.getTime();
        TimeUnit time = TimeUnit.DAYS;
        long difference = time.convert(diff, TimeUnit.MILLISECONDS);
        return (double) (difference / 365);
    }

    private Conta validaConta(Conta conta) throws ContaException{
        double anos = validaData(conta.getData());
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
}
