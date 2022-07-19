package br.com.gerenciadorclientesjava.db.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name="ID_TIPO_CONTA")
    private String tipoConta;

    @OneToOne(cascade = CascadeType.ALL)
    private ClienteEntity clienteEntity;

}
