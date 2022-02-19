package br.com.gerenciadorclientesjava.services.contracts.impl;

import br.com.gerenciadorclientesjava.adapters.conta.ContaEntityAdapter;
import br.com.gerenciadorclientesjava.adapters.conta.ContaServiceAdapter;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioContaEntity;
import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;
import br.com.gerenciadorclientesjava.services.util.ContaUtil;
import br.com.gerenciadorclientesjava.services.util.DataUtil;
import br.com.gerenciadorclientesjava.services.util.StringUtil;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.text.ParseException;
import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {

    private final RepositorioContaEntity contaRepositorio;
    private static ContaService instance;

    @Autowired
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

            StringUtil.validaDocumento(conta.getDocumento());
            ContaUtil.validaPessoaEConta(conta.getDocumento(), conta.getTipoPessoa(), conta.getTipoConta());
            DataUtil.validaNascimento(conta.getTipoPessoa(), conta.getData());
            StringUtil.validaNomeComDezCaracteres(conta.getTipoPessoa(),conta.getNome());
            StringUtil.validaNomeComDezCaracteres(conta.getTipoPessoa(),conta.getNomeDaMae());
            StringUtil.validaRg(conta.getTipoPessoa(), conta.getRg());
            StringUtil.validaSenha(conta.getSenha());
            contaRepositorio.save(new ContaEntityAdapter(conta).getContaEntity());
            return new ContaEntityAdapter(conta).getContaEntity();

        }catch(ContaException | ParseException e){
           throw new ContaException(e.getMessage());
       }

    }

    @Override
    public List<Conta> listarTodos() {

        return new ContaServiceAdapter(contaRepositorio.findAll()).getContas();
    }

    @Override
    public List<Conta> buscaPorDocumento(String documento) throws ContaException {

        StringUtil.validaDocumento(documento);
        if(contaRepositorio.findByDocumento(documento) == null){
            throw new ContaException("Conta Inexistente");
        }
    return new ContaServiceAdapter(contaRepositorio.findByDocumento(documento)).getContas();
    }

    @Override
    public Conta login(String documento, String senha, Integer tipoConta) throws ContaException {

        StringUtil.validaDocumento(documento);
        StringUtil.validaSenha(senha);

        if(contaRepositorio.findByDocumentoESenha(documento, senha, tipoConta) == null){
            throw new ContaException("Conta Inexistente");
        }

        return new ContaServiceAdapter(contaRepositorio.findByDocumentoESenha(documento, senha, tipoConta)).getConta();
    }
}
