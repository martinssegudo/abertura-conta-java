package br.com.gerenciadorclientesjava.apis.contracts.impl;

import br.com.gerenciadorclientesjava.adapters.conta.ContaApiAdapter;
import br.com.gerenciadorclientesjava.adapters.conta.ContaServiceAdapter;
import br.com.gerenciadorclientesjava.apis.contracts.ApiConta;
import br.com.gerenciadorclientesjava.apis.entities.*;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/contas")
@AllArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:8080")
public class ApiContaImpl implements ApiConta {

    private ContaService contaService;

    @PostMapping(value = "/salvarcontapessoafisica")
    @Override
    @ResponseStatus(HttpStatus.OK)
    public ContaPessoaFisicaAPI salvarContaPessoaFisica(@RequestBody @Valid ContaPessoaFisicaAPI contaAPI) throws Exception {


           ContaServiceAdapter adapter = new ContaServiceAdapter(contaAPI);
           contaService.salvarConta(adapter.getConta());
           return contaAPI;


    }

    @PostMapping(value = "/salvarcontapessoajuridica")
    @Override
    @ResponseStatus(HttpStatus.OK)
    public ContaPessoaJuridicaAPI salvarContaPessoaJuridica(@RequestBody @Valid ContaPessoaJuridicaAPI contaAPI) throws Exception {


           ContaServiceAdapter adapter = new ContaServiceAdapter(contaAPI);
           contaService.salvarConta(adapter.getConta());
           return contaAPI;


    }

    @GetMapping(value = "/{documento}")
    @Override
    public List<ContaAPI> contaPorDocumento(@PathVariable String documento) throws Exception {

           List<Conta> contas = contaService.buscaPorDocumento(documento);
           return new ContaApiAdapter(contas).getContasAPI();

    }


    @GetMapping(value = "/login/{documento}/{senha}/{tipoConta}")
    @Override
    public ContaAPI login(@PathVariable String documento, @PathVariable String senha, @PathVariable String tipoConta) throws Exception {

       Conta conta = contaService.login(documento, senha, tipoConta);
       return new ContaApiAdapter(conta).getContaAPI();

    }
}
