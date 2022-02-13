package br.com.gerenciadorclientesjava.db.contracts;

import br.com.gerenciadorclientesjava.db.entities.TipoContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioTipoContaEntity extends JpaRepository<TipoContaEntity, Integer > {

   // @Query(value="select tc from TipoCampeaoEntidade tc " +
  //          "where tc.nomeTecnico like :nomeTecnico")
  //  public TipoContaEntity findByNomeTecnico(@Param("nomeTecnico") String nomeTecnico);
}