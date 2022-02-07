package br.com.gerenciadorclientesjava.adapters.tipoconta;

import br.com.gerenciadorclientesjava.apis.entities.TipoContaAPI;
import br.com.gerenciadorclientesjava.services.entities.TipoConta;
import lombok.Data;

@Data
public class TipoContaAPIAdapter {

    private TipoContaAPI contaAPI;

    public TipoContaAPIAdapter(TipoConta tipoConta) {

        this.contaAPI = converteTipoContaEmTipoContaAPI(tipoConta);

    }

    private TipoContaAPI converteTipoContaEmTipoContaAPI(TipoConta tipoConta) {
        return TipoContaAPI.builder()
                .tipoConta(tipoConta.getTipoConta())
                .build();
    }
}