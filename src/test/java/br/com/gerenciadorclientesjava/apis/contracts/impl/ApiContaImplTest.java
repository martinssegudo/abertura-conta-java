package br.com.gerenciadorclientesjava.apis.contracts.impl;
import br.com.gerenciadorclientesjava.adapters.conta.*;
import br.com.gerenciadorclientesjava.apis.contracts.ApiConta;
import br.com.gerenciadorclientesjava.apis.entities.ContaAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaFisicaAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaJuridicaAPI;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioContaEntity;
import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import br.com.gerenciadorclientesjava.factory.contracts.impl.FabricaInstanciasImpl;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.contracts.impl.ContaServiceImpl;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.factory.contracts.FabricaInstancias;
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
                .documento("29328172802")
                .tipoConta(TipoContaEnum.CORRENTE.ordinal())
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .serasa(600)
                .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .documento("29328172802")
                .tipoConta(TipoContaEnum.CORRENTE.ordinal())
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .serasa(600)
                .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                .build();

        ContaJuridicaAPI contaJuridicaAPI = new ContaJuridicaApiAdapter(conta).getContaJuridicaAPI();
        when(contaService.salvarConta(any(Conta.class))).thenReturn(contaReturn);
        ResponseEntity<ContaJuridicaAPI> result = apiContaImpl.salvarContaJuridica(contaJuridicaAPI);
        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);

    }

    @Test
    public void testApiSalvarContaFisicaErro() throws ContaException, ParseException {

        ContaException exception = new ContaException("Erro");

        ContaEntity contaReturn = ContaEntity.builder()
                .documento("29328172802")
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .serasa(600)
                .nomeDaMae("Severina Maria das Neves")
                .nomeDoPai("Manoel Franco de Alquino")
                .rg("305965827")
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .build();

        when(contaService.salvarConta(any(Conta.class))).thenReturn(contaReturn);
        ResponseEntity<ContaFisicaAPI> result = ResponseEntity.status(406).body(ContaFisicaAPI.builder()
                .erro(exception.getMessage())
                .build());
        Assert.assertEquals(result.getStatusCode(), HttpStatus.NOT_ACCEPTABLE);

    }

    @Test
    public void testApiSalvarContaJuridicaErro() throws ContaException {

        ContaException exception = new ContaException("Erro");

        ContaEntity contaReturn = ContaEntity.builder()
                .documento("12345678901234")
                .tipoConta(TipoContaEnum.CORRENTE.ordinal())
                .data("20/02/2000")
                .nome("Claudio s/a")
                .senha("@Neves123")
                .serasa(600)
                .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                .build();

        when(contaService.salvarConta(any(Conta.class))).thenReturn(contaReturn);
        ResponseEntity<ContaJuridicaAPI> result = ResponseEntity.status(406).body(ContaJuridicaAPI.builder()
                .erro(exception.getMessage())
                .build());
        Assert.assertEquals(result.getStatusCode(), HttpStatus.NOT_ACCEPTABLE);

    }

    @Test
    public void testApiSalvarContaFisicaSucesso() throws ContaException, ParseException {

        Conta conta = Conta.builder()
                .documento("29328172802")
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .serasa(600)
                .nomeDaMae("Severina Maria das Neves")
                .nomeDoPai("Manoel Franco de Alquino")
                .rg("305965827")
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .documento("29328172802")
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .serasa(600)
                .nomeDaMae("Severina Maria das Neves")
                .nomeDoPai("Manoel Franco de Alquino")
                .rg("305965827")
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .build();

        ContaFisicaAPI contaFisicaAPI = new ContaFisicaApiAdapter(conta).getContaFisicaAPI();
        when(contaService.salvarConta(any(Conta.class))).thenReturn(contaReturn);
        ResponseEntity<ContaFisicaAPI> result = apiContaImpl.salvarContaFisica(contaFisicaAPI);
        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void testApiLoginSucesso() throws ContaException, ParseException {

        Conta conta = Conta.builder()
                .documento("29328172802")
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .serasa(600)
                .nomeDaMae("Severina Maria das Neves")
                .nomeDoPai("Manoel Franco de Alquino")
                .rg("305965827")
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .build();

        Conta contaReturn = Conta.builder()
                .documento("29328172802")
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .serasa(600)
                .nomeDaMae("Severina Maria das Neves")
                .nomeDoPai("Manoel Franco de Alquino")
                .rg("305965827")
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .build();

        ContaAPI contaAPI = new ContaApiAdapter(conta).getContaAPI();
        when(contaService.login(conta.getDocumento(),conta.getSenha())).thenReturn(contaReturn);
        ResponseEntity<ContaAPI> result = apiContaImpl.login(conta.getDocumento(),conta.getSenha());
        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);
    }
}

    //Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme