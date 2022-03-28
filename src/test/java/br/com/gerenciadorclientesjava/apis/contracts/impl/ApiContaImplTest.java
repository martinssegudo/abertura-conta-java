package br.com.gerenciadorclientesjava.apis.contracts.impl;

import br.com.gerenciadorclientesjava.adapters.conta.*;
import br.com.gerenciadorclientesjava.apis.entities.*;
import br.com.gerenciadorclientesjava.services.contracts.ContaService;
import br.com.gerenciadorclientesjava.services.entities.enuns.TipoPessoaEnum;
import br.com.gerenciadorclientesjava.services.exceptions.ContaInexistenteException;
import br.com.gerenciadorclientesjava.services.exceptions.LoginException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.util.NestedServletException;

import java.util.Collections;

import static br.com.gerenciadorclientesjava.apis.utils.JsonConvertUtils.asJsonString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;


public class ApiContaImplTest {
        private static final String CONTA_API_URL_PATH = "/api/v1/contas";
   // private static final long VALID_BEER_ID = 1L;
   // private static final long INVALID_BEER_ID = 2l;
   // private static final String BEER_API_SUBPATH_INCREMENT_URL = "/increment";
   // private static final String BEER_API_SUBPATH_DECREMENT_URL = "/decrement";

    private MockMvc mockMvc;

    @Mock
    private ContaService contaService;

    @InjectMocks
    private ApiContaImpl apiContaImpl;

   // @Before
   // public void setUpMockitoAnnotations(){
   //     MockitoAnnotations.initMocks(this);
   // }

  @Before
   public void setUp(){
      MockitoAnnotations.initMocks(this);
      mockMvc = MockMvcBuilders.standaloneSetup(apiContaImpl)
               .setCustomArgumentResolvers( new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }


    @Test
    public void whenPOSTIsCalledThenAContaPessoaFisicaIsOK() throws Exception {
        ContaPessoaFisicaAPI contaAPI = ContaPessoaFisicaAPI.builder()
                .numeroConta(1L)
                .tipoConta("1")
                .clientePessoaFisicaAPI(ClientePessoaFisicaAPI
                        .builder()
                        .idCliente(1L)
                        .cliente("Claudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("Manoel Franco de Alquino")
                        .serasa(600)
                        .rg("305965827")
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .cpf("29328172802")
                        .dataNascimento("20/09/2000")
                        .login(LoginAPI.builder()
                                .id(1L)
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        when(contaService.salvarConta(new ContaServiceAdapter(contaAPI).getConta())).thenReturn(new ContaServiceAdapter(contaAPI).getConta());
        mockMvc.perform(post(CONTA_API_URL_PATH + "/salvarcontapessoafisica")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(contaAPI)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoConta", is(contaAPI.getTipoConta())))
                .andExpect(jsonPath("$.numeroConta", is(contaAPI.getNumeroConta().intValue())))
                .andExpect(jsonPath("$.clientePessoaFisicaAPI.idCliente", is(contaAPI.getClientePessoaFisicaAPI().getIdCliente().intValue())))
                .andExpect(jsonPath("$.clientePessoaFisicaAPI.tipoCliente", is(contaAPI.getClientePessoaFisicaAPI().getTipoCliente())))
                .andExpect(jsonPath("$.clientePessoaFisicaAPI.cliente", is(contaAPI.getClientePessoaFisicaAPI().getCliente())))
                .andExpect(jsonPath("$.clientePessoaFisicaAPI.login.id", is(contaAPI.getClientePessoaFisicaAPI().getLogin().getId().intValue())))
                .andExpect(jsonPath("$.clientePessoaFisicaAPI.login.senha", is(contaAPI.getClientePessoaFisicaAPI().getLogin().getSenha())))
                .andExpect(jsonPath("$.clientePessoaFisicaAPI.dataNascimento", is(contaAPI.getClientePessoaFisicaAPI().getDataNascimento())))
                .andExpect(jsonPath("$.clientePessoaFisicaAPI.nomeDaMae", is(contaAPI.getClientePessoaFisicaAPI().getNomeDaMae())))
                .andExpect(jsonPath("$.clientePessoaFisicaAPI.nomeDoPai", is(contaAPI.getClientePessoaFisicaAPI().getNomeDoPai())))
                .andExpect(jsonPath("$.clientePessoaFisicaAPI.rg", is(contaAPI.getClientePessoaFisicaAPI().getRg())))
                .andExpect(jsonPath("$.clientePessoaFisicaAPI.serasa", is(contaAPI.getClientePessoaFisicaAPI().getSerasa())))
                .andExpect(jsonPath("$.clientePessoaFisicaAPI.cpf", is(contaAPI.getClientePessoaFisicaAPI().getCpf())));
    }

    @Test
    public void whenPOSTIsCalledThenAContaPessoaJuridicaIsOK() throws Exception {
        ContaPessoaJuridicaAPI contaAPI = ContaPessoaJuridicaAPI.builder()
                .numeroConta(1L)
                .tipoConta("1")
                .clientePessoaJuridicaAPI(ClientePessoaJuridicaAPI
                        .builder()
                        .idCliente(1L)
                        .cliente("Claudio S/A")
                        .serasa(600)
                        .tipoCliente(TipoPessoaEnum.JURIDICA.ordinal())
                        .cnpj("12345678901234")
                        .dataAbertura("20/09/2000")
                        .login(LoginAPI.builder()
                                .id(1L)
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();

        when(contaService.salvarConta(new ContaServiceAdapter(contaAPI).getConta())).thenReturn(new ContaServiceAdapter(contaAPI).getConta());
        mockMvc.perform(post(CONTA_API_URL_PATH + "/salvarcontapessoajuridica")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(contaAPI)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tipoConta", is(contaAPI.getTipoConta())))
                .andExpect(jsonPath("$.numeroConta", is(contaAPI.getNumeroConta().intValue())))
                .andExpect(jsonPath("$.clientePessoaJuridicaAPI.idCliente", is(contaAPI.getClientePessoaJuridicaAPI().getIdCliente().intValue())))
                .andExpect(jsonPath("$.clientePessoaJuridicaAPI.tipoCliente", is(contaAPI.getClientePessoaJuridicaAPI().getTipoCliente())))
                .andExpect(jsonPath("$.clientePessoaJuridicaAPI.cliente", is(contaAPI.getClientePessoaJuridicaAPI().getCliente())))
                .andExpect(jsonPath("$.clientePessoaJuridicaAPI.login.id", is(contaAPI.getClientePessoaJuridicaAPI().getLogin().getId().intValue())))
                .andExpect(jsonPath("$.clientePessoaJuridicaAPI.login.senha", is(contaAPI.getClientePessoaJuridicaAPI().getLogin().getSenha())))
                .andExpect(jsonPath("$.clientePessoaJuridicaAPI.dataAbertura", is(contaAPI.getClientePessoaJuridicaAPI().getDataAbertura())))
                .andExpect(jsonPath("$.clientePessoaJuridicaAPI.serasa", is(contaAPI.getClientePessoaJuridicaAPI().getSerasa())))
                .andExpect(jsonPath("$.clientePessoaJuridicaAPI.cnpj", is(contaAPI.getClientePessoaJuridicaAPI().getCnpj())));
    }

    @Test
    public void whenPOSTIsCalledWithoutRequiredFieldInContaPessoaFisicaThenAnErrorIsReturned() throws Exception {
        ContaPessoaFisicaAPI contaAPI = ContaPessoaFisicaAPI.builder()
                .numeroConta(1L)
                .tipoConta("1")
                .clientePessoaFisicaAPI(ClientePessoaFisicaAPI
                        .builder()
                        .idCliente(1L)
                        .cliente("Claudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("Manoel Franco de Alquino")
                        .serasa(600)
                        .rg("305965827")
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .cpf("29328172802")
                        .dataNascimento("20/09/2000")
                        .login(LoginAPI.builder()
                                .id(1L)
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();
        contaAPI.setTipoConta(null);
        mockMvc.perform(post(CONTA_API_URL_PATH + "/salvarcontapessoafisica")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(contaAPI)))
                .andExpect(status().isBadRequest());


    }

    @Test
    public void whenPOSTIsCalledWithoutRequiredFieldInContaPessoaJuridicaThenAnErrorIsReturned() throws Exception {
        ContaPessoaJuridicaAPI contaAPI = ContaPessoaJuridicaAPI.builder()
                .numeroConta(1L)
                .tipoConta("1")
                .clientePessoaJuridicaAPI(ClientePessoaJuridicaAPI
                        .builder()
                        .idCliente(1L)
                        .cliente("Claudio S/A")
                        .serasa(600)
                        .tipoCliente(TipoPessoaEnum.JURIDICA.ordinal())
                        .cnpj("12345678901234")
                        .dataAbertura("20/09/2000")
                        .login(LoginAPI.builder()
                                .id(1L)
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();
        contaAPI.setTipoConta(null);
        mockMvc.perform(post(CONTA_API_URL_PATH + "/salvarcontapessoajuridica")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(contaAPI)))
                .andExpect(status().isBadRequest());
    }

    @Test
   public  void whenGETIsCalledWithValidDocumentoThenOkStatusIsReturned() throws Exception {
        ContaAPI contaAPI = ContaAPI.builder()
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
        when(contaService.buscaPorDocumento(contaAPI.getClienteAPI().getDocumento())).thenReturn(Collections.singletonList(new ContaApiServiceAdapter(contaAPI).getContaAPI()));
        mockMvc.perform(get(CONTA_API_URL_PATH + "/" + contaAPI.getClienteAPI().getDocumento())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].numeroConta", is(contaAPI.getNumeroConta())))
                .andExpect(jsonPath("$[0].clienteAPI.cliente", is(contaAPI.getClienteAPI().getCliente())))
                .andExpect(jsonPath("$[0].tipoConta", is(contaAPI.getTipoConta())));
    }

    @Test
    public void whenGETIsCalledWithoutRegistredDocumentoThenNotFoundStatusReturned() throws Exception {
        ContaAPI contaAPI = ContaAPI.builder()
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
        when(contaService.buscaPorDocumento(contaAPI.getClienteAPI().getDocumento())).thenThrow(ContaInexistenteException.class);
        mockMvc.perform(get(CONTA_API_URL_PATH + "/"+ contaAPI.getClienteAPI().getDocumento())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public  void whenGETIsCalledWithValidLoginThenOkStatusIsReturned() throws Exception {
        ContaAPI contaAPI = ContaAPI.builder()
                .numeroConta(1L)
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
        when(contaService.login(contaAPI.getClienteAPI().getDocumento(),contaAPI.getClienteAPI().getLogin().getSenha(),contaAPI.getTipoConta())).thenReturn(new ContaApiServiceAdapter(contaAPI).getContaAPI());
        mockMvc.perform(get(CONTA_API_URL_PATH + "/login/" + contaAPI.getClienteAPI().getDocumento()+"/"+contaAPI.getClienteAPI().getLogin().getSenha()+"/"+contaAPI.getTipoConta())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.numeroConta", is(contaAPI.getNumeroConta().intValue())))
                .andExpect(jsonPath("$.clienteAPI.cliente", is(contaAPI.getClienteAPI().getCliente())))
                .andExpect(jsonPath("$.tipoConta", is(contaAPI.getTipoConta())));
    }

    @Test
    public  void whenGETIsCalledWithoutRegistredLoginThenNotFoundStatusIsReturned() throws Exception {
        ContaAPI contaAPI = ContaAPI.builder()
                .numeroConta(1L)
                .tipoConta("0")
                .clienteAPI(ClienteAPI
                        .builder()
                        .idCliente(1L)
                        .cliente("Claudio Francisco das Neves")
                        .nomeDaMae("Severina Maria das Neves")
                        .nomeDoPai("")
                        .serasa(600)
                        .rg("305965827")
                        .tipoCliente(TipoPessoaEnum.FISICA.ordinal())
                        .documento("29328172802")
                        .login(LoginAPI.builder()
                                .id(1L)
                                .senha("@Neves123")
                                .build())
                        .build())
                .build();
        when(contaService.login(contaAPI.getClienteAPI().getDocumento(),contaAPI.getClienteAPI().getLogin().getSenha(),contaAPI.getTipoConta())).thenThrow(LoginException.class);
        mockMvc.perform(get(CONTA_API_URL_PATH + "/login/" + contaAPI.getClienteAPI().getDocumento()+"/"+contaAPI.getClienteAPI().getLogin().getSenha()+"/"+contaAPI.getTipoConta())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

}

    //Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme