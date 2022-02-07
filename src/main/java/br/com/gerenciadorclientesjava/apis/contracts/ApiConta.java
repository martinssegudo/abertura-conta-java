package br.com.gerenciadorclientesjava.apis.contracts;

import br.com.gerenciadorclientesjava.apis.entities.ContaAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaFisicaAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaJuridicaAPI;
import br.com.gerenciadorclientesjava.apis.entities.LoginAPI;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApiConta {

    @ApiOperation(value = "Salva uma conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem se salvou o valor"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 406, message = "Quando uma regra for quebrada"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável para o usuário"),
    })
    ResponseEntity<ContaJuridicaAPI> salvarContaJuridica(ContaJuridicaAPI novaConta) throws ContaException;

    @ApiOperation(value = "Salva uma conta")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem se salvou o valor"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 406, message = "Quando uma regra for quebrada"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável para o usuário"),
    })
    ResponseEntity<ContaFisicaAPI> salvarContaFisica(ContaFisicaAPI novaConta) throws ContaException;

    @ApiOperation(value = "Lista todos as contas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna todos as contas"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável para o usuário"),
    })
    ResponseEntity<List<ContaAPI>> listarContas() throws ContaException;

    @ApiOperation(value = "Mostra uma conta pelo numero")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna numero de conta"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável para o usuário"),
    })
    ResponseEntity<ContaAPI> contaPorId(Long id) throws ContaException;

    @ApiOperation(value = "Faz o login do cliente usando conta e senha")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Logado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar esta conta"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável para o usuário"),
    })
    ResponseEntity<String> login(LoginAPI loginApi) throws ContaException;
}
