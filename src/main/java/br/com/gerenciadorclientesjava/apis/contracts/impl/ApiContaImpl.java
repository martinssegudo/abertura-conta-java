package br.com.gerenciadorclientesjava.apis.contracts.impl;

import br.com.gerenciadorclientesjava.adapters.conta.ContaApiAdapter;
import br.com.gerenciadorclientesjava.adapters.conta.ContaServiceAdapter;
import br.com.gerenciadorclientesjava.apis.contracts.ApiConta;
import br.com.gerenciadorclientesjava.apis.entities.*;
import br.com.gerenciadorclientesjava.factory.contracts.FabricaInstancias;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/contas")
@CrossOrigin(origins = "http://localhost:8080")
public class ApiContaImpl implements ApiConta {

    private final ContaService contaService;

    @Autowired
    public ApiContaImpl(FabricaInstancias fabricaInstancias) {
        this.contaService = fabricaInstancias.getContaServiceImple();
    }

    @PostMapping(value = "/salvarfisica")
    @Override
    public ResponseEntity<ContaFisicaAPI> salvarContaFisica(@RequestBody ContaFisicaAPI contaFisicaAPI) {
        ContaServiceAdapter adapter = new ContaServiceAdapter(contaFisicaAPI);
        try {
            contaService.salvarConta(adapter.getConta());
        } catch (ContaException exception) {
            return ResponseEntity.status(406).body(ContaFisicaAPI.builder()
                    .erro(exception.getMessage())
                    .build());
        }
        return ResponseEntity.ok(contaFisicaAPI);
    }

    @PostMapping(value = "/salvarjuridica")
    @Override
    public ResponseEntity<ContaJuridicaAPI> salvarContaJuridica(@RequestBody ContaJuridicaAPI contaJuridicaAPI) {
        ContaServiceAdapter adapter = new ContaServiceAdapter(contaJuridicaAPI);
        try {
            contaService.salvarConta(adapter.getConta());
        } catch (ContaException exception) {
            return ResponseEntity.status(406).body(ContaJuridicaAPI.builder()
                    .erro(exception.getMessage())
                    .build());
        }
        return ResponseEntity.ok(contaJuridicaAPI);
    }

    @GetMapping(value = "/{documento}")
    @Override
    public ResponseEntity<List<ContaAPI>> contaPorDocumento(@PathVariable String documento) throws ContaException {

               List<Conta> conta = contaService.buscaPorDocumento(documento);
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
    public ResponseEntity<ContaAPI> login(@PathVariable String documento, @PathVariable String senha) {
        List<Conta> contas = contaService.listarTodos();
        for (Conta other : contas) {

            if (other.getDocumento().equals(documento) &&
                    other.getSenha().equals(senha)
                   ) {
                return ResponseEntity.ok(new ContaApiAdapter(other).getContaAPI());
            }

        }
        return ResponseEntity.status(403).body(new ContaApiAdapter(Conta.builder()
                .erro("Login Invalido")
                .build()).getContaAPI());
    }
}
