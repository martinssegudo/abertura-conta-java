package br.com.gerenciadorclientesjava.db.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="TB_TIPO_PESSOA")
@GenericGenerator(
        name="SEQ_TIPO_PESSOA",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {
                @Parameter(name="sequence_name",value="SEQ_TIPO_PESSOA"),
                @Parameter(name = "initial_value",value="1"),
                @Parameter(name = "increment_size",value="1")
        }
)
public class TipoPessoaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TIPO_PESSOA")
    @Column(name = "ID_TIPO_PESSOA")
    private Integer id;

    @Column(name = "DS_TIPO_PESSOA")
    private String tipoPessoa;
}