package br.com.gerenciadorclientesjava.services.contracts.impl;

import br.com.gerenciadorclientesjava.adapters.conta.ContaServiceAdapter;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioContaEntity;
import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.entities.enuns.TipoContaEnum;
import br.com.gerenciadorclientesjava.services.entities.enuns.TipoPessoaEnum;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.*;


public class ContaServiceImplTest {
    @Mock
    RepositorioContaEntity contaRepositorio;
    @Mock
    ContaService instance;
    @InjectMocks
    ContaServiceImpl contaServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSalvarContaMaior18Sucesso() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

                when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
                ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
                Assert.assertNotNull(createConta);
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaMenor18Sucesso() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .data(new GregorianCalendar(2022, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .data(new GregorianCalendar(2022, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
        Assert.assertNotNull(createConta);
    }

    @Test(expected = ContaException.class)
    public void testSalvarTipoContaErrada() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(2)
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(2)
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
        Assert.assertNotNull(createConta);
    }

    @Test(expected = ContaException.class)
    public void testSalvarTipoPessoaErrada() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(2)
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(2)
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
        Assert.assertNotNull(createConta);
    }

    @Test(expected = ContaException.class)
    public void testSalvarTipoPessoaEContaErrada() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(2)
                .tipoPessoa(2)
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(2)
                .tipoPessoa(2)
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
        Assert.assertNotNull(createConta);
    }

    @Test(expected = ContaException.class)
    public void testSalvarNomeMenorQueDezCaracteres() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio")
                .senha("@Neves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
        Assert.assertNotNull(createConta);
    }

    @Test
    public void testSalvarSenhaMaiorQueSeisCaracteresECaracteresEspeciais() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
        Assert.assertNotNull(createConta);
    }

    @Test(expected = ContaException.class)
    public void testSalvarSenhaMaiorQueSeisCaracteresSemCaracteresEspeciais() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("11111111")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("11111111")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
        Assert.assertNotNull(createConta);
    }

    @Test(expected = ContaException.class)
    public void testSalvarSenhaMenorQueSeisCaracteresComCaracteresEspeciais() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("@123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("@123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
        Assert.assertNotNull(createConta);
    }

    @Test
    public void testSalvarSenhaMenorQueSeisCaracteresSemCaracteresEspeciais() throws ContaException {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("12345")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .data(new GregorianCalendar(2000, Calendar.FEBRUARY, 3, 22, 13).getTime())
                .nome("Claudio Francisco das Neves")
                .senha("12345")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
        Assert.assertNotNull(createConta);
    }

    @Test
    public void testListarTodasContas() throws Exception {

        List<Conta> contas = new ArrayList<>();
        contas.add(Conta.builder()
                .nome("Cláudio Francisco das Neves")
                .serasa(600)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .documento("29328172802")
                .numeroConta(1L)
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .senha("@NEves123")
                .build());

        List<Conta> contasReturn = new ArrayList<>();
        contasReturn.add(Conta.builder()
                .nome("Cláudio Francisco das Neves")
                .serasa(600)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .documento("29328172802")
                .numeroConta(1L)
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .senha("@NEves123")
                .build());
        when(contaServiceImpl.listarTodos()).thenReturn(contasReturn);
        Assert.assertEquals(contas, contasReturn);
    }

    @Test
    public void testBuscaPorDocumentoSucesso() throws Exception {

        List<Conta> contas = new ArrayList<>();
        contas.add(Conta.builder()
                .nome("Cláudio Francisco das Neves")
                .serasa(600)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .documento("29328172802")
                .numeroConta(1L)
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .senha("@NEves123")
                .build());

        List<Conta> contasReturn = new ArrayList<>();
        contasReturn.add(Conta.builder()
                .nome("Cláudio Francisco das Neves")
                .serasa(600)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .documento("29328172802")
                .numeroConta(1L)
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .senha("@NEves123")
                .build());
        when(contaServiceImpl.buscaPorDocumento("29328172802")).thenReturn(contasReturn);
        Assert.assertEquals(contas, contasReturn);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme