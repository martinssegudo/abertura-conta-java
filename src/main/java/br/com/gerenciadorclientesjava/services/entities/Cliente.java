package br.com.gerenciadorclientesjava.services.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
