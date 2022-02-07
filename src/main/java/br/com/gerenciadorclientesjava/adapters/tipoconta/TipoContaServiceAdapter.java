package br.com.gerenciadorclientesjava.adapters.tipoconta;

import br.com.gerenciadorclientesjava.apis.entities.TipoContaAPI;
import br.com.gerenciadorclientesjava.db.entities.TipoContaEntity;
import br.com.gerenciadorclientesjava.services.entities.TipoConta;
import lombok.Data;

@Data
public class TipoContaServiceAdapter {

    private TipoConta tipoConta;

    public TipoContaServiceAdapter(TipoContaAPI contaAPI){
        this.tipoConta = converterTipoContaAPIEmTipoConta(contaAPI);
    }

    public TipoContaServiceAdapter(TipoContaEntity tipoContaEntity){
        this.tipoConta = converteTipoContaEntityEmTipoConta(tipoContaEntity);
    }

    private TipoConta converterTipoContaAPIEmTipoConta(TipoContaAPI contaAPI){
        return TipoConta.builder()
                .tipoConta(contaAPI.getTipoConta())
                .build();
    }

    private TipoConta converteTipoContaEntityEmTipoConta(TipoContaEntity tipoContaEntity){
        if(tipoContaEntity != null){
            return TipoConta.builder()
                    .id(tipoContaEntity.getId())
                    .tipoConta(tipoContaEntity.getTipoConta())
                    .build();
        }else
            return TipoConta.builder().build();
    }
}
