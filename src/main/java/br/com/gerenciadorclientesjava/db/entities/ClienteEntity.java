package br.com.gerenciadorclientesjava.db.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="TB_CLIENTE")
@GenericGenerator(
        name="SEQ_CLIENTE",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name="sequence_name",value="SEQ_CLIENTE"),
                @Parameter(name = "initial_value",value="1"),
                @Parameter(name = "increment_size",value="1")
        }
)
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator ="SEQ_CLIENTE")
    @Column(name="ID_ClIENTE")
    private Long idCliente;

    @Column(name="DS_DOCUMENTO")
    private String documento;

    @Column(name="DS_CLIENTE")
    private String cliente;

    @Column(name="ID_TIPO_CLIENTE")
    private Integer tipoCliente;

    @Column(name="DATA")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private String data;
    @Column(name="DS_RG")
    private String rg;
    @Column(name="DS_SERASA")
    private Integer serasa;
    @Column(name="DS_NOME_PAI")
    private String nomeDoPai;
    @Column(name="DS_NOME_MAE")
    private String nomeDaMae;

    @OneToOne(cascade = CascadeType.ALL)
    private LoginEntity loginEntity;

}
