package br.com.gerenciadorclientesjava.services.entities;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Login {

    private Long id;
    private String senha;

}
