package br.com.gerenciadorclientesjava.apis.contracts.impl;

import br.com.gerenciadorclientesjava.adapters.conta.ContaApiAdapter;
import br.com.gerenciadorclientesjava.adapters.conta.ContaFisicaApiAdapter;
import br.com.gerenciadorclientesjava.adapters.conta.ContaJuridicaApiAdapter;
import br.com.gerenciadorclientesjava.adapters.conta.ContaServiceAdapter;
import br.com.gerenciadorclientesjava.apis.contracts.ApiConta;
import br.com.gerenciadorclientesjava.apis.entities.ContaAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaFisicaAPI;
import br.com.gerenciadorclientesjava.apis.entities.ContaJuridicaAPI;
import br.com.gerenciadorclientesjava.apis.entities.LoginAPI;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioContaEntity;
import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import br.com.gerenciadorclientesjava.factory.contracts.impl.FabricaInstanciasImpl;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.contracts.impl.ContaServiceImpl;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.factory.contracts.FabricaInstancias;
import io.swagger.annotations.Api;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.*;

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
    public void testSalvarContaFisica() throws Exception {

        Conta conta = Conta.builder()
                .documento("29328172802")
                .tipoConta(0)
                .tipoPessoa(0)
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .nomeDoPai("Manoel Franco de Alquino")
                .rg("305965827")
                .serasa(600)
                .senha("@Neves123")
                .erro(null)
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .documento("29328172802")
                .tipoConta(0)
                .tipoPessoa(0)
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .nomeDoPai("Manoel Franco de Alquino")
                .rg("305965827")
                .serasa(600)
                .senha("@Neves123")
                .erro(null)
                .build();
        ContaFisicaAPI contaFisicaAPI = new ContaFisicaApiAdapter(conta).getContaFisicaAPI();
        when(contaService.salvarConta(conta)).thenReturn(contaReturn);
        ResponseEntity<ContaFisicaAPI> result = apiContaImpl.salvarContaFisica(contaFisicaAPI);
        Assert.assertNull(result);
    }

    @Test
    public void testSalvarContaJuridica() throws Exception {

        Conta conta = Conta.builder()
                .documento("29328172802")
                .tipoConta(0)
                .tipoPessoa(0)
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .nomeDoPai("Manoel Franco de Alquino")
                .rg("305965827")
                .serasa(600)
                .senha("@Neves123")
                .erro(null)
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .documento("29328172802")
                .tipoConta(0)
                .tipoPessoa(0)
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .nomeDoPai("Manoel Franco de Alquino")
                .rg("305965827")
                .serasa(600)
                .senha("@Neves123")
                .erro(null)
                .build();
        ContaJuridicaAPI contaJuridicaAPI = new ContaJuridicaApiAdapter(conta).getContaJuridicaAPI();
        when(contaServiceImpl.salvarConta(any(Conta.class))).thenReturn(contaReturn);
        ResponseEntity<ContaJuridicaAPI> result = apiContaImpl.salvarContaJuridica(contaJuridicaAPI);
        Assert.assertNotNull(result.getStatusCode());
    }

    @Test
    public void testContaPorDocumento() throws Exception {
        when(contaService.buscaPorDocumento(anyString())).thenReturn(List.of(new Conta(0L, 0, "Claudio Francisco das Neves", new Date(2000, 2, 3), 0, "29328172802", "305965827", 630, "Manoel Franco de Alquino", "Severina Maria das Neves", "@Neves123", null)));
        ResponseEntity<List<ContaAPI>> result = apiContaImpl.contaPorDocumento("29328172802");
        Assert.assertEquals(ResponseEntity.ok().body(new ContaApiAdapter(Conta.builder()
                .numeroConta(0L)
                .tipoPessoa(0)
                .nome("Claudio Francisco das Neves")
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .tipoConta(0)
                .documento("29328172802")
                .serasa(0)
                .erro(null)
                .build()).getContasAPI()), result);
    }

    @Test
    public void testLogin() {
        when(contaService.listarTodos()).thenReturn(List.of(new Conta(1L, 1, "Claudio Francisco das Neves", new Date(2000, 3, 20), 0, "29328172802", "2222", 630, "mmmmm", "jjjjj", "@Neves123", null)));
        ResponseEntity<ContaAPI> result = apiContaImpl.login("documento", "senha");
        Assert.assertNull(result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme