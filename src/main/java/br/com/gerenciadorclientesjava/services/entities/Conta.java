package br.com.gerenciadorclientesjava.services.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Conta {

            private Long numeroConta;
            private Integer tipoPessoa;
            private String nome;

            @DateTimeFormat(pattern = "dd/MM/yyyy")
            private String data;

            private Integer tipoConta;
            private String documento;
            private String rg;
            private Integer serasa;
            private String nomeDoPai;
            private String nomeDaMae;
            private String senha;
            private String erro;
}
