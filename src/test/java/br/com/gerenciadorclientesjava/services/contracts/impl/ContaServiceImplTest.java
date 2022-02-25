package br.com.gerenciadorclientesjava.services.contracts.impl;

import br.com.gerenciadorclientesjava.adapters.conta.ContaServiceAdapter;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioContaEntity;
import br.com.gerenciadorclientesjava.db.entities.ClienteEntity;
import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import br.com.gerenciadorclientesjava.db.entities.LoginEntity;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.entities.Cliente;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.entities.Login;
import br.com.gerenciadorclientesjava.services.entities.enuns.TipoContaEnum;
import br.com.gerenciadorclientesjava.services.entities.enuns.TipoPessoaEnum;
import br.com.gerenciadorclientesjava.services.exceptions.ContaException;
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
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("")
                        .data("20/02/2000")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())

                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("")
                        .data("20/02/2000")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())

                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }


    @Test
    public void testSalvarContaFisicaMaior18Sucesso() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .documento("29328172802")
                        .rg("305965827")
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .data("20/02/2000")
                        .cliente("Claudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .build();

                when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
                //Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaFisicaNomeDaMaeEmBrancoErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .nomeDaMae("")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .idCliente(1L)
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .rg("305965827")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .nomeDaMae("")
                        .loginEntity(LoginEntity.builder()
                                .id(1L)
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(conta)).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarTipoContaErrada() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta("2")
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta("2")
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaTipoPessoaErradaErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(2)
                        .documento("29328172802")
                        .data("20/02/2000")
                        .cliente("Claudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(2)
                        .documento("29328172802")
                        .data("20/02/2000")
                        .cliente("Claudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();


        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaTipoPessoaEContaErradaErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta("2")
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(2)
                        .documento("29328172802")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta("2")
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(2)
                        .documento("29328172802")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarNomeMenorQueDezCaracteresErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .rg("305965827")
                        .data("20/02/2000")
                        .cliente("Cláudio")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .rg("305965827")
                        .data("20/02/2000")
                        .cliente("Cláudio")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test
    public void testSalvarSenhaMaiorQueSeisCaracteresECaracteresEspeciaisSucesso() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .rg("305965827")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .rg("305965827")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarSenhaMaiorQueSeisCaracteresSemCaracteresEspeciaisErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta("1")
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .rg("305965827")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("")
                        .serasa(600)
                        .loginEntity(LoginEntity.builder()
                                .senha("11111111")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta("1")
                .clienteEntity(ClienteEntity.builder()
                        .idCliente(1L)
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .rg("305965827")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("")
                        .serasa(600)
                        .loginEntity(LoginEntity.builder()
                                .id(1L)
                                .senha("11111111")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarSenhaMenorQueSeisCaracteresComCaracteresEspeciaisErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .rg("305965827")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@#123")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .rg("305965827")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@#123")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarSenhaMenorQueSeisCaracteresSemCaracteresEspeciaisErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .rg("305965827")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("12345")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .rg("305965827")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("12345")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaPessoaFisicaRgInvalidoErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .rg("30ABCVF")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .rg("30ABCVF")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarRgNuloErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .rg(null)
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .rg(null)
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaPessoaFisicaCPFNuloErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento(null)
                        .rg("305965827")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento(null)
                        .rg("305965827")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarTipoDeContaPoupancaNaoCompativelComDocumentoErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("12345678901234")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("12345678901234")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaPessoaJuridicaComCPFErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.JURIDICA.ordinal())
                        .documento("29328172802")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.JURIDICA.ordinal())
                        .documento("29328172802")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaPessoaJuridicaComDocumentoNuloErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.JURIDICA.ordinal())
                        .documento(null)
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.JURIDICA.ordinal())
                        .documento(null)
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaPessoaJuridicaComDocumentoInvalidoErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.JURIDICA.ordinal())
                        .documento("293vfdcs")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.JURIDICA.ordinal())
                        .documento("293vfdcs")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaPessoaFisicaComCNPJErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("12345678901234")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();
        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(String.valueOf(TipoContaEnum.CORRENTE.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("12345678901234")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test(expected = ContaException.class)
    public void testSalvarContaPessoaJuridicaNaoCompativelComTipoDeContaPoupancaErro() throws Exception {

        ContaEntity conta = ContaEntity.builder()
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.JURIDICA.ordinal())
                        .documento("12345678901234")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        ContaEntity contaReturn = ContaEntity.builder()
                .numeroConta(1L)
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .clienteEntity(ClienteEntity.builder()
                        .tipoCliente(TipoPessoaEnum.JURIDICA.ordinal())
                        .documento("12345678901234")
                        .data("20/02/2000")
                        .cliente("Cláudio Francisco das Neves")
                        .loginEntity(LoginEntity.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        when(contaRepositorio.save(any(ContaEntity.class))).thenReturn(contaReturn);
        Conta createConta = contaServiceImpl.salvarConta(new ContaServiceAdapter(conta).getConta());
    }

    @Test
    public void testListarTodasContas() throws Exception {

        List<Conta> contasReturn = new ArrayList<>();
        contasReturn.add(Conta.builder()
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .cliente(Cliente.builder()
                        .tipoPessoa(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .data("20/02/2000")
                        .nome("Cláudio Francisco das Neves")
                        .login(Login.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build());
        when(contaServiceImpl.listarTodos()).thenReturn(contasReturn);
    }

    @Test
    public void testBuscaPorDocumentoSucesso() throws Exception {

        List<Conta> contasReturn = new ArrayList<>();
        contasReturn.add(Conta.builder()
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .cliente(Cliente.builder()
                        .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                        .documento("29328172802")
                        .data("20/02/2000")
                        .nome("Cláudio Francisco das Neves")
                        .login(Login.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build());
        when(contaServiceImpl.buscaPorDocumento("29328172802")).thenReturn(contasReturn);
    }

    @Test(expected = ContaException.class)
    public void testBuscaPorDocumentoVazioFalha() throws Exception {

        List<Conta> contas = new ArrayList<>();
        contas.add(Conta.builder()
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                        .cliente(Cliente.builder()
                                .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                                .documento("29328172802")
                                .data("20/02/2000")
                                .nome("Cláudio Francisco das Neves")
                                .login(Login.builder()
                                        .senha("@Neves123")
                                        .build())
                                .build())
                        .build());

        List<Conta> contasReturn = new ArrayList<>();
        contasReturn.add(Conta.builder()
                .tipoConta(String.valueOf(TipoContaEnum.POUPANCA.ordinal()))
                .cliente(Cliente.builder()
                        .tipoPessoa(TipoPessoaEnum.JURIDICA.ordinal())
                        .documento("29328172802")
                        .data("20/02/2000")
                        .nome("Cláudio Francisco das Neves")
                        .login(Login.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build());
        when(contaServiceImpl.buscaPorDocumento("")).thenReturn(contasReturn);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme