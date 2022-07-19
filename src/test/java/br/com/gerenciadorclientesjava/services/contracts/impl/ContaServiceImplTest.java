package br.com.gerenciadorclientesjava.services.contracts.impl;

import br.com.gerenciadorclientesjava.adapters.conta.*;
import br.com.gerenciadorclientesjava.apis.entities.*;
import br.com.gerenciadorclientesjava.db.contracts.RepositorioContaEntity;
import br.com.gerenciadorclientesjava.services.entities.Conta;
import br.com.gerenciadorclientesjava.services.entities.enuns.TipoPessoaEnum;
import br.com.gerenciadorclientesjava.services.exceptions.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class ContaServiceImplTest {

    //private static final long INVALID_USER_ID = 1L;

    @Mock
    private RepositorioContaEntity contaRepository;

    @InjectMocks
    private ContaServiceImpl contaServiceImpl;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

    }



    @Test
    public void whenContaPessoaFisicaInformedThenItShouldBeCreated() throws Exception {
        ContaPessoaFisicaAPI expectedContaAPI =  ContaPessoaFisicaAPI.builder()
                .numeroConta(1L)
                .tipoConta("1")
                .clientePessoaFisicaAPI(ClientePessoaFisicaAPI
                        .builder()
                        .idCliente(1L)
                        .dataNascimento("20/02/2000")
                        .cliente("Claudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("Manoel Franco de Alquino")
                        .serasa(600)
                        .rg("305965827")
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .cpf("29328172802")
                        .login(LoginAPI.builder()
                                .id(1L)
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();
        Conta expectedSavedConta = new ContaServiceAdapter(expectedContaAPI).getConta();
        when(contaRepository.findByDocumento(expectedContaAPI.getClientePessoaFisicaAPI().getCpf())).thenReturn(Optional.empty());
        when(contaRepository.save(new ContaEntityAdapter(expectedSavedConta).getContaEntity())).thenReturn(new ContaEntityAdapter(expectedSavedConta).getContaEntity());
        ContaPessoaFisicaAPI createdContaAPI = new ContaPessoaFisicaApiAdapter(contaServiceImpl.salvarConta(expectedSavedConta)).getContaPessoaFisicaApi();

        assertThat(createdContaAPI.getTipoConta(), is(equalTo(expectedContaAPI.getTipoConta())));
        assertThat(createdContaAPI.getNumeroConta(), is(equalTo(expectedContaAPI.getNumeroConta())));
        assertThat(createdContaAPI.getClientePessoaFisicaAPI(), is(equalTo(expectedContaAPI.getClientePessoaFisicaAPI())));
    }

    @Test
    public void whenContaPessoaJuridicaInformedThenItShouldBeCreated() throws Exception {
        ContaPessoaJuridicaAPI expectedContaAPI =  ContaPessoaJuridicaAPI.builder()
                .numeroConta(1L)
                .tipoConta("0")
                .clientePessoaJuridicaAPI(ClientePessoaJuridicaAPI
                        .builder()
                        .idCliente(1L)
                        .dataAbertura("20/02/2000")
                        .cliente("Claudio Francisco das Neves")
                        .serasa(600)
                        .tipoCliente(TipoPessoaEnum.JURIDICA.ordinal())
                        .cnpj("12345678901234")
                        .login(LoginAPI.builder()
                                .id(1L)
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();
        Conta expectedSavedConta = new ContaServiceAdapter(expectedContaAPI).getConta();
        when(contaRepository.findByDocumento(expectedContaAPI.getClientePessoaJuridicaAPI().getCnpj())).thenReturn(Optional.empty());
        when(contaRepository.save(new ContaEntityAdapter(expectedSavedConta).getContaEntity())).thenReturn(new ContaEntityAdapter(expectedSavedConta).getContaEntity());
        ContaPessoaJuridicaAPI createdContaAPI = new ContaPessoaJuridicaApiAdapter(contaServiceImpl.salvarConta(expectedSavedConta)).getContaPessoaJuridicaApi();

        assertThat(createdContaAPI.getTipoConta(), is(equalTo(expectedContaAPI.getTipoConta())));
        assertThat(createdContaAPI.getNumeroConta(), is(equalTo(expectedContaAPI.getNumeroConta())));
        assertThat(createdContaAPI.getClientePessoaJuridicaAPI(), is(equalTo(expectedContaAPI.getClientePessoaJuridicaAPI())));
    }

    @Test
   public void whenAlreadyRegistredContaInformedThenAnExceptionShouldBeThrown()  {
        ContaAPI expectedContaAPI = ContaAPI.builder()
                .tipoConta("1")
                .clienteAPI(ClienteAPI
                        .builder()
                        .cliente("Claudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("")
                        .serasa(600)
                        .rg("305965827")
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .login(LoginAPI.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();
        Conta duplicatedConta = new ContaApiServiceAdapter(expectedContaAPI).getContaAPI();
        when(contaRepository.findByDuplicates(expectedContaAPI.getClienteAPI().getDocumento(), "1")).thenReturn(Optional.of(new ContaEntityAdapter(duplicatedConta).getContaEntity()));
        assertThrows(ContaDuplicadaException.class, () -> contaServiceImpl.salvarConta(new ContaApiServiceAdapter(expectedContaAPI).getContaAPI()));
    }

    @Test
    public void whenValidContaDocumentoIsGivenReturnAConta() throws Exception {
        List<ContaAPI> expectedFoundContaAPI = new ArrayList<>();
                 expectedFoundContaAPI.add(ContaAPI.builder()
                .tipoConta("1")
                .clienteAPI(ClienteAPI
                        .builder()
                        .cliente("Claudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("")
                        .serasa(600)
                        .rg("305965827")
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .login(LoginAPI.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build());

        List<Conta> expectedFoundConta = new ContaApiServiceAdapter(expectedFoundContaAPI).getContasAPI();
        when(contaRepository.findByDocumento(expectedFoundConta.get(0).getCliente().getDocumento())).thenReturn(Optional.of(Collections.singletonList(new ContaEntityAdapter(expectedFoundConta.get(0)).getContaEntity())));
        List<ContaAPI> foundContasAPI = new ContaApiAdapter(contaServiceImpl.buscaPorDocumento(expectedFoundContaAPI.get(0).getClienteAPI().getDocumento())).getContasAPI();
        assertThat(foundContasAPI, is(equalTo(expectedFoundContaAPI)));
    }

    @Test
    public void whenNotRegistredContaDocumentoIsGivenThrowAnException() {
        ContaAPI expectedFoundContaAPI =  ContaAPI.builder()
                .tipoConta("1")
                .clienteAPI(ClienteAPI
                        .builder()
                        .cliente("Claudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("")
                        .serasa(600)
                        .rg("305965827")
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .login(LoginAPI.builder()
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();
        when(contaRepository.findByDocumento(expectedFoundContaAPI.getClienteAPI().getDocumento())).thenReturn(Optional.empty());
        assertThrows(ContaInexistenteException.class, () -> contaServiceImpl.buscaPorDocumento(expectedFoundContaAPI.getClienteAPI().getDocumento()));
    }



      @Test
      public void whenBuscaPorDocumentoIsCalledThenReturnAnEmptyListContas() {

        when(contaRepository.findByDocumento("29328172802")).thenReturn(Optional.of(Collections.EMPTY_LIST));
        assertThrows(ContaInexistenteException.class, () -> new ContaApiAdapter(contaServiceImpl.buscaPorDocumento("29328172802")).getContasAPI());
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme