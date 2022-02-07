package br.com.gerenciadorclientesjava.adapters.tipopessoa;

import br.com.gerenciadorclientesjava.apis.entities.TipoPessoaAPI;
import br.com.gerenciadorclientesjava.db.entities.TipoPessoaEntity;
import br.com.gerenciadorclientesjava.services.entities.TipoPessoa;
import lombok.Data;

@Data
public class TipoPessoaServiceAdapter {

    private TipoPessoa tipoPessoa;

    public TipoPessoaServiceAdapter(TipoPessoaAPI pessoaAPI) {
        this.tipoPessoa = converterTipoPessoaAPIEmTipoPessoa(pessoaAPI);
    }

    public TipoPessoaServiceAdapter(TipoPessoaEntity tipoPessoaEntity) {
        this.tipoPessoa = converteTipoPessoaEntidadeEmTipoPessoa(tipoPessoaEntity);
    }

    private TipoPessoa converterTipoPessoaAPIEmTipoPessoa(TipoPessoaAPI pessoaAPI) {
        return TipoPessoa.builder()
                .tipoPessoa(pessoaAPI.getTipoPessoa())
                .build();
    }

    private TipoPessoa converteTipoPessoaEntidadeEmTipoPessoa(TipoPessoaEntity tipoPessoaEntity) {
        if (tipoPessoaEntity != null) {
            return TipoPessoa.builder()
                    .id(tipoPessoaEntity.getId())
                    .tipoPessoa(tipoPessoaEntity.getTipoPessoa())
                    .build();
        } else
            return TipoPessoa.builder().build();
    }
}
