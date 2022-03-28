package br.com.gerenciadorclientesjava.services.contracts;

import br.com.gerenciadorclientesjava.services.entities.Conta;

import java.util.List;

public interface ContaService {

    Conta salvarConta(Conta conta) throws Exception;

    List<Conta> buscaPorDocumento(String documento) throws  Exception;

    void verificaDuplicados(String documento, String tipoConta) throws Exception;

    Conta login(String documento, String senha, String tipoConta) throws Exception;

}