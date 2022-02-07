package br.com.gerenciadorclientesjava.adapters.tipopessoa;

import br.com.gerenciadorclientesjava.apis.entities.TipoPessoaAPI;
import br.com.gerenciadorclientesjava.services.entities.TipoPessoa;
import lombok.Data;

@Data
public class TipoPessoaAPIAdapter {

    private TipoPessoaAPI pessoaAPI;

    public TipoPessoaAPIAdapter(TipoPessoa tipoPessoa) {

        this.pessoaAPI = converteTipoPessoaEmTipoPessoaAPI(tipoPessoa);

    }

    private TipoPessoaAPI converteTipoPessoaEmTipoPessoaAPI(TipoPessoa tipoPessoa) {
        return TipoPessoaAPI.builder()
                .tipoPessoa(tipoPessoa.getTipoPessoa())
                .build();
    }
}
