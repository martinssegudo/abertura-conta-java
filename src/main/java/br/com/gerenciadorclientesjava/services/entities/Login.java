package br.com.gerenciadorclientesjava.services.entities;

import br.com.gerenciadorclientesjava.db.entities.ClienteEntity;
import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Login {

    private Long id;
    private String senha;
}
