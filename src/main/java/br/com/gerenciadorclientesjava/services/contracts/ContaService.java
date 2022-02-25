package br.com.gerenciadorclientesjava.services.contracts;

import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;

import java.util.List;

public interface ContaService {

    Conta salvarConta(Conta conta) throws ContaException;

    List<Conta> listarTodos();

    List<Conta> buscaPorDocumento(String documento) throws ContaException;

    void verificaDuplicados(String documento, String tipoConta) throws ContaException;

    Conta login(String documento, String senha, String tipoConta) throws ContaException;
}