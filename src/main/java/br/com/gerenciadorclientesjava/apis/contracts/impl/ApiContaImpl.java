package br.com.gerenciadorclientesjava.apis.contracts.impl;

import br.com.gerenciadorclientesjava.adapters.conta.ContaApiAdapter;
import br.com.gerenciadorclientesjava.adapters.conta.ContaServiceAdapter;
import br.com.gerenciadorclientesjava.apis.contracts.ApiConta;
import br.com.gerenciadorclientesjava.apis.entities.*;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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


    @PostMapping(value = "/salvarfisica")
    @Override
    public ResponseEntity<ContaFisicaAPI> salvarContaFisica(@RequestBody ContaFisicaAPI contaFisicaAPI) throws ContaException{

        List<Conta> contas = contaService.listarTodos();
        for (Conta conta: contas) {
            if (conta.getDocumento().equals(contaFisicaAPI.getCpf()) && conta.getTipoConta().equals(contaFisicaAPI.getTipoConta())) {
                return ResponseEntity.status(406).body(ContaFisicaAPI.builder()
                        .erro("Conta Duplicada favor digitar outro CPF ou Escolher outro tipo de conta")
                        .build());
            }
        }
        try {
            ContaServiceAdapter adapter = new ContaServiceAdapter(contaFisicaAPI);
            contaService.salvarConta(adapter.getConta());
        }catch(ContaException e){
            ResponseEntity.status(406).body(ContaFisicaAPI.builder()
                    .erro(e.getMessage())
                    .build());
        }
        return ResponseEntity.ok(contaFisicaAPI);
    }


    @PostMapping(value = "/salvarjuridica")
    @Override
    public ResponseEntity<ContaJuridicaAPI> salvarContaJuridica(@RequestBody ContaJuridicaAPI contaJuridicaAPI) throws ContaException {

        List<Conta> contas = contaService.listarTodos();
        for (Conta conta: contas) {
            if (conta.getDocumento().equals(contaJuridicaAPI.getCnpj()) && conta.getTipoConta().equals(contaJuridicaAPI.getTipoConta())) {
                return ResponseEntity.status(406).body(ContaJuridicaAPI.builder()
                        .erro("Conta Duplicada favor digitar outro CNPJ")
                        .build());
            }
        }
        try {
            ContaServiceAdapter adapter = new ContaServiceAdapter(contaJuridicaAPI);
            contaService.salvarConta(adapter.getConta());
        }catch(ContaException e){
           return ResponseEntity.status(406).body(ContaJuridicaAPI.builder()
                   .erro(e.getMessage())
                   .build());
        }
        return ResponseEntity.ok(contaJuridicaAPI);
    }

    @GetMapping(value = "/{documento}")
    @Override
    public ResponseEntity<List<ContaAPI>> contaPorDocumento(@PathVariable String documento) throws ContaException {

               List<Conta> conta = this.contaService.buscaPorDocumento(documento);
               if(new ContaApiAdapter(conta).getContasAPI().isEmpty()){
                   conta.add(Conta.builder()
                           .erro("Não existe conta cadastrada para o documento informado")
                           .build());
                   return ResponseEntity.status(403).body(new ContaApiAdapter(conta).getContasAPI());
               }

        return ResponseEntity.ok(new ContaApiAdapter(conta).getContasAPI());
    }


    @GetMapping(value = "/login/{documento}/{senha}")
    @ResponseBody
    @Override
    public ResponseEntity<ContaAPI> login(@PathVariable String documento, @PathVariable String senha) throws ContaException {

        Conta conta = contaService.login(documento, senha);
        if(new ContaApiAdapter(conta).getContaAPI().equals("")){
            Conta.builder()
                    .erro("Não existe conta cadastrada para o documento informado")
                    .build();
            return ResponseEntity.status(403).body(new ContaApiAdapter(conta).getContaAPI());
        }

        return ResponseEntity.ok(new ContaApiAdapter(conta).getContaAPI());

   }
}
