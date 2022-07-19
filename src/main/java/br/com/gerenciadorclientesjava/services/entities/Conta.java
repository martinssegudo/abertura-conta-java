package br.com.gerenciadorclientesjava.services.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Conta {

            private Long numeroConta;
            private String tipoConta;
            private Cliente cliente;

}
