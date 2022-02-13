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
public class ContaAPI {

       private Long numeroConta;
       private Integer tipoPessoa;
       private String nome;

       @DateTimeFormat(pattern = "dd/MM/yyyy")
       private Date data;

       private Integer tipoConta;
       private String documento;
       private Integer serasa;
       private String erro;
}
