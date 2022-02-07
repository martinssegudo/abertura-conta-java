package br.com.gerenciadorclientesjava.factory.contracts.impl;

import br.com.gerenciadorclientesjava.db.contracts.RepositorioContaEntity;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioTipoContaEntity;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioTipoPessoaEntity;
import br.com.gerenciadorclientesjava.factory.contracts.FabricaInstancias;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.contracts.impl.ContaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FabricaInstanciasImpl implements FabricaInstancias {

    private RepositorioContaEntity repositorioContaEntity;
    private RepositorioTipoContaEntity repositorioTipoContaEntity;
    private RepositorioTipoPessoaEntity repositorioTipoPessoaEntity;


    @Autowired
    private FabricaInstanciasImpl(RepositorioContaEntity repositorioContaEntity,
                                  RepositorioTipoContaEntity repositorioTipoContaEntity,
                                  RepositorioTipoPessoaEntity repositorioTipoPessoaEntity) {
        this.repositorioContaEntity = repositorioContaEntity;
        this.repositorioTipoContaEntity = repositorioTipoContaEntity;
        this.repositorioTipoPessoaEntity = repositorioTipoPessoaEntity;
    }


    @Override
    public ContaService getContaServiceImple() {
        return ContaServiceImpl.getInstance(repositorioContaEntity);
    }

}
