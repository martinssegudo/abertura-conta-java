package br.com.gerenciadorclientesjava.services.contracts.impl;

import br.com.gerenciadorclientesjava.adapters.conta.ContaEntityAdapter;
import br.com.gerenciadorclientesjava.adapters.conta.ContaServiceAdapter;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioContaEntity;
import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;
import br.com.gerenciadorclientesjava.services.util.DataUtil;
import br.com.gerenciadorclientesjava.services.util.StringUtil;

import java.util.List;

public class ContaServiceImpl implements ContaService {

    private final RepositorioContaEntity contaRepositorio;
    private static ContaService instance;

    public ContaServiceImpl(RepositorioContaEntity contaRepositorio){
        this.contaRepositorio = contaRepositorio;
    }

    public static ContaService getInstance(RepositorioContaEntity contaRepositorio){
        if(instance == null){
            instance = new ContaServiceImpl(contaRepositorio);
        }
        return instance;
    }

    @Override
    public ContaEntity salvarConta(Conta conta) throws ContaException {

        try {
            StringUtil.validaPessoaEConta(conta.getTipoPessoa(), conta.getTipoConta());
            DataUtil.validaNascimento(conta.getTipoPessoa(), conta.getData());
            StringUtil.validaDocumento(conta.getDocumento());
            StringUtil.validaSenha(conta);
            contaRepositorio.save(new ContaEntityAdapter(conta).getContaEntity());
            // conta = StringUtil.validaConta(conta);
        }catch(ContaException e){
            e.getMessage();
        }
        return new ContaEntityAdapter(conta).getContaEntity();
    }

    @Override
    public List<Conta> listarTodos() {

        return new ContaServiceAdapter(contaRepositorio.findAll()).getContas();
    }

    @Override
    public List<Conta> buscaPorDocumento(String documento) throws ContaException {

        if(contaRepositorio.findByDocumento(documento) == null){
            throw new ContaException("Conta Inexistente");
        }
    return new ContaServiceAdapter(contaRepositorio.findByDocumento(documento)).getContas();
    }
}
