package br.com.gerenciadorclientesjava.apis.contracts.impl;

import br.com.gerenciadorclientesjava.adapters.conta.ContaApiAdapter;
import br.com.gerenciadorclientesjava.adapters.conta.ContaServiceAdapter;
import br.com.gerenciadorclientesjava.apis.contracts.ApiConta;
import br.com.gerenciadorclientesjava.apis.entities.*;
import br.com.gerenciadorclientesjava.factory.contracts.FabricaInstancias;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;
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

    @GetMapping(value = "/listar")
    @Override
    public ResponseEntity<List<ContaAPI>> listarContas() {
        return ResponseEntity.ok(new ContaApiAdapter(contaService.listarTodos()).getContasAPI());
    }

    @GetMapping(value = "/{numeroConta}")
    @Override
    public ResponseEntity<ContaAPI> contaPorId(@PathVariable Long numeroConta) {
        return ResponseEntity.ok(new ContaApiAdapter(contaService.buscaPorId(numeroConta)).getContaAPI());
    }


    @PostMapping(value = "/login")
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody LoginAPI loginApi) {
        List<Conta> contas = contaService.listarTodos();
        for (Conta other : contas) {
            if (other.getNumeroConta().equals(loginApi.getNumeroConta()) &&
                    other.getSenha().equals(loginApi.getSenha())
                   ) {
                return ResponseEntity.ok()
                        .body("Logado com Sucesso");
            }
        }
        return ResponseEntity.ok()
                .body("Falha ao Logar");
    }
}
