package br.com.gerenciadorclientesjava.apis.contracts;

import br.com.gerenciadorclientesjava.apis.entities.ContaAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaPessoaFisicaAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaPessoaJuridicaAPI;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;

public interface ApiConta {

    @ApiOperation(value = "Salva uma conta pessoa fisica")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem se salvou o valor"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 406, message = "Quando uma regra for quebrada"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável para o usuário"),
    })
   ContaPessoaFisicaAPI salvarContaPessoaFisica(ContaPessoaFisicaAPI novaConta) throws Exception;

    @ApiOperation(value = "Salva uma conta pessoa juridica")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem se salvou o valor"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 406, message = "Quando uma regra for quebrada"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável para o usuário"),
    })
    ContaPessoaJuridicaAPI salvarContaPessoaJuridica(ContaPessoaJuridicaAPI novaConta) throws Exception;


    @ApiOperation(value = "Mostra uma conta pelo numero do documento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna numero de conta"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável para o usuário"),
    })
    List<ContaAPI> contaPorDocumento(String documento) throws Exception;

    @ApiOperation(value = "Faz o login do cliente usando conta e senha")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Logado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar esta conta"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável para o usuário"),
    })
    ContaAPI login(String documento, String senha, String tipoConta) throws Exception;
}
