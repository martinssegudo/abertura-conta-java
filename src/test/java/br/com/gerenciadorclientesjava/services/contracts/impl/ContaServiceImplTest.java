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

    @Test(expected = ContaException.class)
    public void testSalvarContaDocumentoEmBrancoErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("")
                .data("20/02/2020")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }


    @Test
    public void testSalvarContaFisicaMaior18Sucesso() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .rg("305965827")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .build();

                when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
                ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaFisicaNomeDaMaeEmBrancoErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .rg("305965827")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .rg("305965827")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("")
                .senha("@Neves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaPessoaFisicaMenor18Erro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .data("20/02/2022")
                .rg("305965827")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .data("20/02/2022")
                .rg("305965827")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarTipoContaErrada() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(2)
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(2)
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaTipoPessoaErradaErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(2)
                .documento("29328172802")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(2)
                .documento("29328172802")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaTipoPessoaEContaErradaErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(2)
                .tipoPessoa(2)
                .documento("29328172802")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(2)
                .tipoPessoa(2)
                .documento("29328172802")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarNomeMenorQueDezCaracteresErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .rg("305965827")
                .data("20/02/2000")
                .nome("Claudio")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .rg("305965827")
                .data("20/02/2000")
                .nome("Claudio")
                .senha("@Neves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test
    public void testSalvarSenhaMaiorQueSeisCaracteresECaracteresEspeciaisSucesso() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.CORRENTE.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .rg("305965827")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .rg("305965827")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .senha("@Neves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected= ContaException.class)
    public void testSalvarSenhaMaiorQueSeisCaracteresSemCaracteresEspeciaisErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.CORRENTE.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .rg("305965827")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .senha("11111111")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .rg("305965827")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .senha("11111111")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarSenhaMenorQueSeisCaracteresComCaracteresEspeciaisErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .data("20/02/2000")
                .rg("305965827")
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .senha("@#123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .rg("305965827")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .senha("@#123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarSenhaMenorQueSeisCaracteresSemCaracteresEspeciaisErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .rg("305965827")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .senha("12345")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .rg("305965827")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .senha("12345")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaPessoaFisicaRgInvalidoErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .rg("30596abc")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .data("20/02/2000")
                .rg("30596abc")
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .senha("@NEves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarRgNuloErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .rg(null)
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("29328172802")
                .rg(null)
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .nomeDaMae("Severina Maria das Neves")
                .senha("@NEves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaPessoaFisicaCPFNuloErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento(null)
                .data("20/02/2000")
                .rg("305965827")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento(null)
                .data("20/02/2000")
                .rg("305965827")
                .nome("Claudio Francisco das Neves")
                .senha("@NEves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarTipoDeContaPoupancaNaoCompativelComDocumentoErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("12345678901234")
                .rg("305965827")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("12345678901234")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@NEves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaPessoaJuridicaComCPFErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.CORRENTE.ordinal())
                .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                .documento("29328172802")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.CORRENTE.ordinal())
                .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                .documento("29328172802")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@NEves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaPessoaJuridicaComDocumentoNuloErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.CORRENTE.ordinal())
                .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                .documento(null)
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.CORRENTE.ordinal())
                .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                .documento(null)
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@NEves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaPessoaJuridicaComDocumentoInvalidoErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.CORRENTE.ordinal())
                .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                .documento("1234abc")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.CORRENTE.ordinal())
                .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                .documento("1234abc")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@NEves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaPessoaFisicaComCNPJErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.CORRENTE.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("12345678901234")
                .rg("305965827")
                .nomeDaMae("Severina Maria das Neves")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("12345678901234")
                .rg("305965827")
                .nomeDaMae("Severina Maria das Neves")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@NEves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaPessoaJuridicaNaoCompativelComTipoDeContaPoupancaErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                .documento("12345678901234")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@Neves123")
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(TipoContaEnum.POUPANCA.ordinal())
                .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                .documento("12345678901234")
                .data("20/02/2000")
                .nome("Claudio Francisco das Neves")
                .senha("@NEves123")
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        ContaEntity createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test
    public void testListarTodasContas() throws Exception {

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
    }

    @Test
    public void testBuscaPorDocumentoSucesso() throws Exception {

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
    }

    @Test(expected = ContaException.class)
    public void testBuscaPorDocumentoVazioFalha() throws Exception {

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
        when(contaServiceImpl.buscaPorDocumento("")).thenReturn(contasReturn);
        Assert.assertEquals(contas, contasReturn);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme