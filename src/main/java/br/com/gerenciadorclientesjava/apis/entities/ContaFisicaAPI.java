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
public class ContaFisicaAPI {

            private Long numeroConta;
            private Integer tipoPessoa;
            private String nomeCompleto;

            @DateTimeFormat(pattern = "dd/MM/yyyy")
            private Date dataNascimento;

            private Integer tipoConta;
            private String cpf;
            private String rg;
            private Integer serasa;
            private String nomeDoPai;
            private String nomeDaMae;
            private String senha;
            private String erro;
}
