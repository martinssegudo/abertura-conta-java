package br.com.gerenciadorclientesjava.services.contracts;

import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;

import java.util.List;

public interface ContaService {

    ContaEntity salvarConta(Conta conta) throws ContaException;

    List<Conta> listarTodos();

    List<Conta> buscaPorDocumento(String documento) throws ContaException;
}