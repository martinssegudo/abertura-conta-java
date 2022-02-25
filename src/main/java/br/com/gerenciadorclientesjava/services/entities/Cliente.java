package br.com.gerenciadorclientesjava.services.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cliente {

    private Long idCliente;
    private Integer tipoPessoa;
    private String nome;
    private String data;
    private String documento;
    private String rg;
    private Integer serasa;
    private String nomeDoPai;
    private String nomeDaMae;
    private Login login;
}
