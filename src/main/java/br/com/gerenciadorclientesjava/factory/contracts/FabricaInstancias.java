package br.com.gerenciadorclientesjava.factory.contracts;

import br.com.gerenciadorclientesjava.services.contracts.ContaService;

public interface FabricaInstancias {

    ContaService getContaServiceImple();

}
