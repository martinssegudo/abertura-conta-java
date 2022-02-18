package br.com.gerenciadorclientesjava.apis.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContaJuridicaAPI {

            private Long numeroConta;
            private Integer tipoPessoa;
            private String razaoSocial;

            @DateTimeFormat(pattern = "dd/MM/yyyy")
            private String dataAbertura;

            private Integer tipoConta;
            private String cnpj;
            private Integer serasa;
            private String senha;
            private String erro;
}
