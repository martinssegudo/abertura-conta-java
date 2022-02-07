package br.com.gerenciadorclientesjava.services.contracts;

import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;

import java.util.List;

public interface ContaService {

    boolean salvarConta(Conta conta) throws ContaException;

    List<Conta> listarTodos();

    Conta buscaPorId(Long id);
}