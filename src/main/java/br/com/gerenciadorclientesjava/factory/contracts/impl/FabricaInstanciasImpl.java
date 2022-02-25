package br.com.gerenciadorclientesjava.factory.contracts.impl;

import br.com.gerenciadorclientesjava.db.contracts.RepositorioClienteEntity;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioContaEntity;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioLoginEntity;
import br.com.gerenciadorclientesjava.factory.contracts.FabricaInstancias;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.contracts.impl.ContaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FabricaInstanciasImpl implements FabricaInstancias {

    private RepositorioContaEntity repositorioContaEntity;
    private RepositorioLoginEntity repositorioLoginEntity;
    private RepositorioClienteEntity repositorioClienteEntity;

    @Autowired
    private FabricaInstanciasImpl(RepositorioContaEntity repositorioContaEntity, RepositorioLoginEntity repositorioLoginEntity, RepositorioClienteEntity repositorioClienteEntity){
        this.repositorioContaEntity = repositorioContaEntity;
        this.repositorioLoginEntity = repositorioLoginEntity;
        this.repositorioClienteEntity = repositorioClienteEntity;
    }


    @Override
    public ContaService getContaServiceImple() {
        return ContaServiceImpl.getInstance(this.repositorioContaEntity, this.repositorioLoginEntity, this.repositorioClienteEntity);
    }

}
