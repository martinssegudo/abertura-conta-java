package br.com.gerenciadorclientesjava.factory.contracts.impl;

import br.com.gerenciadorclientesjava.db.contracts.RepositorioContaEntity;
import br.com.gerenciadorclientesjava.factory.contracts.FabricaInstancias;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.contracts.impl.ContaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FabricaInstanciasImpl implements FabricaInstancias {

    private RepositorioContaEntity repositorioContaEntity;

    @Autowired
    private FabricaInstanciasImpl(RepositorioContaEntity repositorioContaEntity) {
        this.repositorioContaEntity = repositorioContaEntity;
    }


    @Override
    public ContaService getContaServiceImple() {
        return ContaServiceImpl.getInstance(repositorioContaEntity);
    }

}
