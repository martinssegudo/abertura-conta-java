package br.com.gerenciadorclientesjava.apis.entities;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginAPI {


    private Long id;

    @NotNull
    @Min(6)
    private String senha;

}