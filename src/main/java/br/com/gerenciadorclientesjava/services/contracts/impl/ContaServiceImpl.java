package br.com.gerenciadorclientesjava.services.contracts.impl;

import br.com.gerenciadorclientesjava.adapters.conta.ContaEntityAdapter;
import br.com.gerenciadorclientesjava.adapters.conta.ContaServiceAdapter;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioClienteEntity;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioContaEntity;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioLoginEntity;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;
import br.com.gerenciadorclientesjava.services.util.ContaUtil;
import br.com.gerenciadorclientesjava.services.util.DataUtil;
import br.com.gerenciadorclientesjava.services.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class ContaServiceImpl implements ContaService {

    private final RepositorioContaEntity contaRepositorio;
    private final RepositorioLoginEntity loginRepositorio;
    private final RepositorioClienteEntity clienteRepositorio;

    private static ContaService instance;

    @Autowired
    public ContaServiceImpl(RepositorioContaEntity contaRepositorio, RepositorioLoginEntity loginRepositorio,RepositorioClienteEntity clienteRepositorio){
        this.contaRepositorio = contaRepositorio;
        this.loginRepositorio = loginRepositorio;
        this.clienteRepositorio = clienteRepositorio;
    }

    public static ContaService getInstance(RepositorioContaEntity contaRepositorio, RepositorioLoginEntity loginRepositorio, RepositorioClienteEntity clienteRepositorio){
        if(instance == null){
            instance = new ContaServiceImpl(contaRepositorio, loginRepositorio, clienteRepositorio);
        }
        return instance;
    }

    @Override
    public Conta salvarConta(Conta conta) throws ContaException {

        try {

            StringUtil.validaDocumento(conta.getCliente().getDocumento());
            ContaUtil.validaPessoaEConta(conta.getCliente().getDocumento(), conta.getCliente().getTipoPessoa(), conta.getTipoConta());
            DataUtil.validaNascimento(conta.getCliente().getTipoPessoa(), conta.getCliente().getData());
            StringUtil.validaNomeComDezCaracteres(conta.getCliente().getTipoPessoa(),conta.getCliente().getNome());
            StringUtil.validaNomeComDezCaracteres(conta.getCliente().getTipoPessoa(),conta.getCliente().getNomeDaMae());
            StringUtil.validaRg(conta.getCliente().getTipoPessoa(), conta.getCliente().getRg());
            StringUtil.validaSenha(conta.getCliente().getLogin().getSenha());
            contaRepositorio.save(new ContaEntityAdapter(conta).getContaEntity());
            return new ContaServiceAdapter(new ContaEntityAdapter(conta).getContaEntity()).getConta();

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
        if(contaRepositorio.findByDocumento(documento).isEmpty()){
            throw new ContaException("Conta Inexistente");
        }
    return new ContaServiceAdapter(contaRepositorio.findByDocumento(documento)).getContas();
    }

    @Override
    public Conta login(String documento, String senha, String tipoConta) throws ContaException {

        StringUtil.validaDocumento(documento);
        StringUtil.validaSenha(senha);

        if(loginRepositorio.findByDocumentoESenha(documento, senha, tipoConta) == null){
            throw new ContaException("Conta Inexistente");
        }

        return new ContaServiceAdapter(loginRepositorio.findByDocumentoESenha(documento, senha, tipoConta)).getConta();
    }

    @Override
    public void verificaDuplicados(String documento, String tipoConta) throws ContaException {

        StringUtil.validaDocumento(documento);

        if(contaRepositorio.findByDuplicates(documento, tipoConta) > 0){
            throw new ContaException("Conta Duplicada");
        }

        new ContaServiceAdapter(contaRepositorio.findByDocumento(documento));

    }
}
