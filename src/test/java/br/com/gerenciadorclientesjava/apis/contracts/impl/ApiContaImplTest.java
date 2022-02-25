package br.com.gerenciadorclientesjava.apis.contracts.impl;
import br.com.gerenciadorclientesjava.adapters.conta.*;
import br.com.gerenciadorclientesjava.apis.contracts.ApiConta;
import br.com.gerenciadorclientesjava.apis.entities.ContaAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaPessoaFisicaAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaPessoaJuridicaAPI;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioContaEntity;
import br.com.gerenciadorclientesjava.factory.contracts.impl.FabricaInstanciasImpl;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.contracts.impl.ContaServiceImpl;
import br.com.gerenciadorclientesjava.services.entities.Cliente;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.factory.contracts.FabricaInstancias;
import br.com.gerenciadorclientesjava.services.entities.Login;
import br.com.gerenciadorclientesjava.services.entities.enuns.TipoContaEnum;
import br.com.gerenciadorclientesjava.services.entities.enuns.TipoPessoaEnum;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ApiContaImplTest {

    @Mock
    RepositorioContaEntity contaRepositorio;
    @Mock
    FabricaInstancias fabricaInstancias;
    @Mock
    ContaService contaService;
    @Mock
    ApiConta apiConta;
    @InjectMocks
    FabricaInstanciasImpl fabricaInstanciasImpl;
    @InjectMocks
    ApiContaImpl apiContaImpl;
    @InjectMocks
    ContaServiceImpl contaServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testApiSalvarContaJuridicaSucesso() throws ContaException, ParseException {

        Conta conta =  Conta.builder()
                .cliente(Cliente.builder()
                        .documento("12345678901234")
                        .data("20/02/2000")
                        .nome("Cláudio Francisco das Neves")
                        .serasa(600)
                        .login(Login.builder()
                                .senha("@Neves123")
                                .build())
                        .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                        .build())
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .build();

        Conta contaReturn = Conta.builder()
                .cliente(Cliente.builder()
                        .documento("12345678901234")
                        .data("20/02/2000")
                        .nome("Cláudio Francisco das Neves")
                        .serasa(600)
                        .login(Login.builder()
                                .senha("@Neves123")
                                .build())
                        .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                        .build())
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .build();

        ContaPessoaJuridicaAPI contaAPI = new ContaPessoaJuridicaApiAdapter(conta).getContaPessoaJuridicaApi();
        when(contaService.salvarConta(any(Conta.class))).thenReturn(contaReturn);
        ResponseEntity<ContaPessoaJuridicaAPI> result = apiContaImpl.salvarContaPessoaJuridica(contaAPI);
        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void testApiSalvarContaFisicaErro() throws ContaException, ParseException {

        ContaException exception = new ContaException("Erro");

        Conta contaReturn = Conta.builder()
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .cliente(Cliente.builder()
                        .documento("29328172802")
                        .data("20/02/2000")
                        .nome("Claudio Francisco das Neves")
                        .login(Login.builder()
                                .senha("@Neves123")
                                .build())
                        .serasa(600)
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("Manoel Franco de Alquino")
                        .rg("305965827")
                        .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                        .build())
                .build();

        when(contaService.salvarConta(any(Conta.class))).thenReturn(contaReturn);
        ResponseEntity<ContaAPI> result = ResponseEntity.status(406).body(ContaAPI.builder()
                .erro(exception.getMessage())
                .build());
        Assert.assertEquals(result.getStatusCode(), HttpStatus.NOT_ACCEPTABLE);

    }

    @Test
    public void testApiSalvarContaJuridicaErro() throws ContaException {

        ContaException exception = new ContaException("Erro");

        Conta contaReturn = Conta.builder()
                .cliente(Cliente.builder()
                        .documento("12345678901234")
                        .data("20/02/2000")
                        .nome("Cláudio S/A")
                        .login(Login.builder()
                                .senha("@Neves123")
                                .build())
                        .serasa(600)
                        .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                        .build())
                .build();

        when(contaService.salvarConta(any(Conta.class))).thenReturn(contaReturn);
        ResponseEntity<ContaAPI> result = ResponseEntity.status(406).body(ContaAPI.builder()
                .erro(exception.getMessage())
                .build());
        Assert.assertEquals(result.getStatusCode(), HttpStatus.NOT_ACCEPTABLE);

    }

    @Test
    public void testApiSalvarContaFisicaSucesso() throws ContaException, ParseException {

        Conta conta = Conta.builder()
                .cliente(Cliente.builder()
                        .documento("29328172802")
                        .data("20/02/2000")
                        .nome("Cláudio Francisco das Neves")
                        .login(Login.builder()
                                .senha("@Neves123")
                                .build())
                        .serasa(600)
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("Manoel Franco de Alquino")
                        .rg("305965827")
                        .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                        .build())
                .build();

        Conta contaReturn = Conta.builder()
                .cliente(Cliente.builder()
                        .documento("29328172802")
                        .data("20/02/2000")
                        .nome("Cláudio Francisco das Neves")
                        .login(Login.builder()
                                .senha("@Neves123")
                                .build())
                        .serasa(600)
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("Manoel Franco de Alquino")
                        .rg("305965827")
                        .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                        .build())
                .build();

        ContaPessoaFisicaAPI contaAPI = new ContaPessoaFisicaApiAdapter(conta).getContaPessoaFisicaApi();
        when(contaService.salvarConta(any(Conta.class))).thenReturn(contaReturn);
        ResponseEntity<ContaPessoaFisicaAPI> result = apiContaImpl.salvarContaPessoaFisica(contaAPI);
        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testApiLoginSucesso() throws ContaException, ParseException {

        Conta conta = Conta.builder()
                .cliente(Cliente.builder()
                        .documento("29328172802")
                        .data("20/02/2000")
                        .nome("Cláudio Francisco das Neves")
                        .login(Login.builder()
                                .senha("@Neves123")
                                .build())
                        .serasa(600)
                        .nomeDoPai("Manoel Franco de Alquino")
                        .nomeDaMae("Severina Maria das Neves")
                        .rg("305965827")
                        .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                        .build())
                .build();

        Conta contaReturn = Conta.builder()
                .cliente(Cliente.builder()
                        .documento("29328172802")
                        .data("20/02/2000")
                        .nome("Cláudio Francisco das Neves")
                        .login(Login.builder()
                                .senha("@Neves123")
                                .build())
                        .serasa(600)
                        .nomeDoPai("Manoel Franco de Alquino")
                        .nomeDaMae("Severina Maria das Neves")
                        .rg("305965827")
                        .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                        .build())
                .build();

        ContaAPI contaAPI = new ContaApiAdapter(conta).getContaAPI();
        when(contaService.login(conta.getCliente().getDocumento(),conta.getCliente().getLogin().getSenha(),conta.getTipoConta())).thenReturn(contaReturn);
        ResponseEntity<ContaAPI> result = apiContaImpl.login(conta.getCliente().getDocumento(),conta.getCliente().getLogin().getSenha(),conta.getTipoConta());
        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testApiLoginFalha() throws ContaException, ParseException {

        ContaException exception = new ContaException("Erro");

        Conta conta = Conta.builder()
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .cliente(Cliente.builder()
                        .documento("29328172800")
                        .data("20/02/2000")
                        .nome("Claudio Francisco das Neves")
                        .login(Login.builder()
                                .senha("@Neves000")
                                .build())
                        .serasa(600)
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("Manoel Franco de Alquino")
                        .rg("305965827")
                        .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                        .build())
                .build();

        Conta contaReturn = Conta.builder()
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .cliente(Cliente.builder()
                        .documento("29328172800")
                        .data("20/02/2000")
                        .nome("Claudio Francisco das Neves")
                        .login(Login.builder()
                                .senha("@Neves000")
                                .build())
                        .serasa(600)
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("Manoel Franco de Alquino")
                        .rg("305965827")
                        .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                        .build())
                .build();

        ContaAPI contaAPI = new ContaApiAdapter(conta).getContaAPI();
        when(contaService.login("111111","222222", String.valueOf(TipoContaEnum.POUPANCA.ordinal()))).thenReturn(contaReturn);
        ResponseEntity<ContaAPI> result = ResponseEntity.status(406).body(ContaAPI.builder()
                .erro(exception.getMessage())
                .build());
        Assert.assertEquals(result.getStatusCode(), HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void testApiBuscaDocumentoFalha() throws ContaException, ParseException {

        Conta conta = Conta.builder()
                .cliente(Cliente.builder()
                        .documento("29328172802")
                        .data("20/02/2000")
                        .nome("Cláudio Francisco das Neves")
                        .login(Login.builder()
                                .senha("@Neves000")
                                .build())
                        .serasa(600)
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("Manoel Franco de Alquino")
                        .rg("305965827")
                        .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                        .build())
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .build();

        Conta contaReturn = Conta.builder()
                .cliente(Cliente.builder()
                        .documento("29328172802")
                        .data("20/02/2000")
                        .nome("Cláudio Francisco das Neves")
                        .login(Login.builder()
                                .senha("@Neves000")
                                .build())
                        .serasa(600)
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("Manoel Franco de Alquino")
                        .rg("305965827")
                        .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                        .build())
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .build();

        List<Conta> contas = new ArrayList<>();
        contas.add(contaReturn);

        ContaAPI contaAPI = new ContaApiAdapter(conta).getContaAPI();
        when(contaService.buscaPorDocumento("293281")).thenReturn(contas);
        ResponseEntity<List<ContaAPI>> result = ResponseEntity.status(406).body(new ContaApiAdapter(conta).getContasAPI());
        Assert.assertEquals(result.getStatusCode(), HttpStatus.NOT_ACCEPTABLE);
    }

    @Test
    public void testApiBuscaDocumentoSucesso() throws ContaException, ParseException {

        Conta conta = Conta.builder()
                .cliente(Cliente.builder()
                        .documento("29328172800")
                        .data("20/02/2000")
                        .nome("Claudio Francisco das Neves")
                        .login(Login.builder()
                                .senha("@Neves000")
                                .build())
                        .serasa(600)
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("Manoel Franco de Alquino")
                        .rg("305965827")
                        .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                        .build())
                .build();

        Conta contaReturn = Conta.builder()
                .cliente(Cliente.builder()
                        .documento("29328172800")
                        .data("20/02/2000")
                        .nome("Claudio Francisco das Neves")
                        .login(Login.builder()
                                .senha("@Neves000")
                                .build())
                        .serasa(600)
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("Manoel Franco de Alquino")
                        .rg("305965827")
                        .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                        .build())
                .build();


        List<Conta> contas = new ArrayList<>();
        contas.add(contaReturn);

        ContaAPI contaAPI = new ContaApiAdapter(conta).getContaAPI();
        when(contaService.buscaPorDocumento("29328172800")).thenReturn(contas);
        ResponseEntity<List<ContaAPI>> result = apiContaImpl.contaPorDocumento(contaAPI.getClienteAPI().getDocumento());
        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);
    }
}

    //Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme