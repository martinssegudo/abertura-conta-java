package br.com.gerenciadorclientesjava.services.contracts.impl;

import br.com.gerenciadorclientesjava.adapters.conta.ContaEntityAdapter;
import br.com.gerenciadorclientesjava.adapters.conta.ContaServiceAdapter;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioContaEntity;
import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.exceptions.*;
import br.com.gerenciadorclientesjava.services.util.ContaUtil;
import br.com.gerenciadorclientesjava.services.util.DataUtil;
import br.com.gerenciadorclientesjava.services.util.StringUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ContaServiceImpl implements ContaService {

    private final RepositorioContaEntity contaRepositorio;
    private static ContaService instance;

    public static ContaService getInstance(RepositorioContaEntity contaRepositorio){
        if(instance == null){
            instance = new ContaServiceImpl(contaRepositorio);
        }
        return instance;
    }

    @Override
    public Conta salvarConta(Conta conta) throws Exception {

            verificaDuplicados(conta.getCliente().getDocumento(), conta.getTipoConta());
            StringUtil.validaDocumento(conta.getCliente().getDocumento());
            ContaUtil.validaPessoaEConta(conta.getCliente().getDocumento(), conta.getCliente().getTipoPessoa(), conta.getTipoConta());
            DataUtil.validaNascimento(conta.getCliente().getTipoPessoa(), conta.getCliente().getData());
            StringUtil.validaNomeComDezCaracteres(conta.getCliente().getTipoPessoa(),conta.getCliente().getNome());
            StringUtil.validaNomeComDezCaracteres(conta.getCliente().getTipoPessoa(),conta.getCliente().getNomeDaMae());
            StringUtil.validaRg(conta.getCliente().getTipoPessoa(), conta.getCliente().getRg());
            StringUtil.validaSenha(conta.getCliente().getLogin().getSenha());
            contaRepositorio.save(new ContaEntityAdapter(conta).getContaEntity());
            return new ContaServiceAdapter(new ContaEntityAdapter(conta).getContaEntity()).getConta();

    }

    @Override
    public List<Conta> buscaPorDocumento(String documento) throws Exception {

        StringUtil.validaDocumento(documento);
        List<ContaEntity> foundContas = contaRepositorio.findByDocumento(documento).orElseThrow(() -> new ContaInexistenteException());
        if (!foundContas.isEmpty()){
            return new ContaServiceAdapter(foundContas).getContasEntity();
        }else{
            throw new ContaInexistenteException();
        }
    }


    @Override
    public void verificaDuplicados(String documento, String tipoConta) throws Exception {

            StringUtil.validaDocumento(documento);

            if (contaRepositorio.findByDuplicates(documento, tipoConta).isPresent()) {
                throw new ContaDuplicadaException();
            }

    }

    @Override
    public Conta login(String documento, String senha, String tipoConta) throws Exception {

        StringUtil.validaDocumento(documento);
        StringUtil.validaSenha(senha);

        ContaEntity contaEntity = contaRepositorio.findByDocumentoESenha(documento, senha, tipoConta)
                .orElseThrow(() -> new LoginException());

        return new ContaServiceAdapter(contaEntity).getConta();

    }
}
