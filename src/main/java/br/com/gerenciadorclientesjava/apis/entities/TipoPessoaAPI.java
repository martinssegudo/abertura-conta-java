package br.com.gerenciadorclientesjava.apis.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TipoPessoaAPI {

    private Integer idTipoPessoa;
    private String tipoPessoa;

}
