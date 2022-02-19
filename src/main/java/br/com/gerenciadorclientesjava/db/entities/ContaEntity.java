package br.com.gerenciadorclientesjava.db.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="TB_CONTA")
@GenericGenerator(
        name="SEQ_CONTA",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name="sequence_name",value="SEQ_CONTA"),
                @Parameter(name = "initial_value",value="1"),
                @Parameter(name = "increment_size",value="1")
        }
)
public class ContaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="SEQ_CONTA")
    @Column(name="ID_CONTA")
    private Long numeroConta;

    @Column(name="DS_NOME_RAZAO_SOCIAL")
    private String nome;

    @Column(name="ID_TIPO_PESSOA")
    private Integer tipoPessoa;

    @Column(name="DATA")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String data;

    @Column(name="ID_TIPO_CONTA")
    private Integer tipoConta;

    @Column(name="DS_DOCUMENTO")
    private String documento;

    @Column(name="DS_RG")
    private String rg;
    @Column(name="DS_SERASA")
    private Integer serasa;
    @Column(name="DS_NOME_PAI")
    private String nomeDoPai;
    @Column(name="DS_NOME_MAE")
    private String nomeDaMae;

    @Column(name="DS_SENHA")
    private String senha;

    private String erro;

}
