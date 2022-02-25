package br.com.gerenciadorclientesjava.apis.contracts.impl;

import br.com.gerenciadorclientesjava.adapters.conta.ContaApiAdapter;
import br.com.gerenciadorclientesjava.adapters.conta.ContaServiceAdapter;
import br.com.gerenciadorclientesjava.apis.contracts.ApiConta;
import br.com.gerenciadorclientesjava.apis.entities.*;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.entities.Cliente;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.entities.Login;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/contas")
@CrossOrigin(origins = "http://localhost:8080")
public class ApiContaImpl implements ApiConta {

    private final ContaService contaService;

   @Autowired
       public ApiContaImpl(ContaService contaService) {
       this.contaService = contaService;
   }



   //@Autowired
   //public ApiContaImpl(FabricaInstancias fabricaInstancias) {
   //     this.contaService = fabricaInstancias.getContaServiceImple();
   // }




    @PostMapping(value = "/salvarcontapessoafisica")
    @Override
    public ResponseEntity<ContaPessoaFisicaAPI> salvarContaPessoaFisica(@RequestBody ContaPessoaFisicaAPI contaAPI) throws ContaException {


        try {
            contaService.verificaDuplicados(contaAPI.getClientePessoaFisicaAPI().getCpf(), contaAPI.getTipoConta());
            ContaServiceAdapter adapter = new ContaServiceAdapter(contaAPI);
            System.out.print(adapter.getConta());
            contaService.salvarConta(adapter.getConta());
        }catch(ContaException e){
           return ResponseEntity.status(406).body(ContaPessoaFisicaAPI.builder()
                   .erro(e.getMessage())
                   .build());
        }
        return ResponseEntity.ok(contaAPI);
    }

    @PostMapping(value = "/salvarcontapessoajuridica")
    @Override
    public ResponseEntity<ContaPessoaJuridicaAPI> salvarContaPessoaJuridica(@RequestBody ContaPessoaJuridicaAPI contaAPI) throws ContaException {


        try {
            contaService.verificaDuplicados(contaAPI.getClientePessoaJuridicaAPI().getCnpj(), contaAPI.getTipoConta());
            ContaServiceAdapter adapter = new ContaServiceAdapter(contaAPI);
            System.out.print(adapter.getConta());
            contaService.salvarConta(adapter.getConta());
        }catch(ContaException e){
            return ResponseEntity.status(406).body(ContaPessoaJuridicaAPI.builder()
                    .erro(e.getMessage())
                    .build());
        }
        return ResponseEntity.ok(contaAPI);
    }

    @GetMapping(value = "/{documento}")
    @Override
    public ResponseEntity<List<ContaAPI>> contaPorDocumento(@PathVariable String documento) throws ContaException {

       List<Conta> conta = new ArrayList<>();

       try {

           conta = this.contaService.buscaPorDocumento(documento);

       }catch(ContaException e){
           conta.add(Conta.builder()
                           .cliente(Cliente.builder()
                                   .tipoPessoa(null)
                                   .login(Login.builder()
                                           .senha("")
                                           .build())
                                   .build())
                           .erro("Conta Inexistente")
                           .build());
           return ResponseEntity.status(403).body(new ContaApiAdapter(conta).getContasAPI());
       }

        return ResponseEntity.ok(new ContaApiAdapter(conta).getContasAPI());


    }


    @GetMapping(value = "/login/{documento}/{senha}/{tipoConta}")
    @ResponseBody
    @Override
    public ResponseEntity<ContaAPI> login(@PathVariable String documento, @PathVariable String senha, @PathVariable String tipoConta) throws ContaException {

       Conta conta;

        try {

            conta = contaService.login(documento, senha, tipoConta);

        }catch(ContaException e){
           conta = Conta.builder()
                    .cliente(Cliente.builder()
                            .tipoPessoa(null)
                            .login(Login.builder()
                                    .senha("")
                                    .build())
                            .build())
                    .erro("Conta Inexistente")
                    .build();
            return ResponseEntity.status(403).body(new ContaApiAdapter(conta).getContaAPI());
        }

        return ResponseEntity.ok(new ContaApiAdapter(conta).getContaAPI());


    }
}
