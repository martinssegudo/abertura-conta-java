package br.com.gerenciadorclientesjava.db.contracts;

import br.com.gerenciadorclientesjava.db.entities.TipoPessoaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioTipoPessoaEntity extends JpaRepository<TipoPessoaEntity, Integer> {

   // @Query(value="select tc from TipoCampeaoEntidade tc " +
  //          "where tc.nomeTecnico like :nomeTecnico")
  //  public TipoCampeaoEntidade findByNomeTecnico(@Param("nomeTecnico") String nomeTecnico);
}