package br.com.gerenciadorclientesjava.apis.contracts;

import br.com.gerenciadorclientesjava.apis.entities.ContaAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaFisicaAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaJuridicaAPI;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.el.parser.ParseException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ApiConta {

    @ApiOperation(value = "Salva uma conta de pessoa Jurídica")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem se salvou o valor"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 406, message = "Quando uma regra for quebrada"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável para o usuário"),
    })
    ResponseEntity<ContaJuridicaAPI> salvarContaJuridica(ContaJuridicaAPI novaConta) throws ContaException;

    @ApiOperation(value = "Salva uma conta de Pessoa Fisica")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna uma mensagem se salvou o valor"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 406, message = "Quando uma regra for quebrada"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável para o usuário"),
    })
    ResponseEntity<ContaFisicaAPI> salvarContaFisica(ContaFisicaAPI novaConta) throws ContaException , ParseException, java.text.ParseException;

    @ApiOperation(value = "Mostra uma conta pelo numero do documento")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna numero de conta"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável para o usuário"),
    })
    ResponseEntity<List<ContaAPI>> contaPorDocumento(String documento) throws ContaException;

    @ApiOperation(value = "Faz o login do cliente usando conta e senha")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Logado com sucesso"),
            @ApiResponse(code = 403, message = "Você não tem permissão para acessar esta conta"),
            @ApiResponse(code = 500, message = "Retornará uma mensagem amigável para o usuário"),
    })
    ResponseEntity<ContaAPI> login(String documento, String senha) throws ContaException;
}
