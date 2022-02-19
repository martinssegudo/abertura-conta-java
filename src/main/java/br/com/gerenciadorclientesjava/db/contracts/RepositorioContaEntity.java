package br.com.gerenciadorclientesjava.db.contracts;

import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioContaEntity extends JpaRepository<ContaEntity, Long>{
   List<ContaEntity> findByDocumento(String documento);

   @Query(value = "select c from ContaEntity c where c.documento like %?1% and "
           + "c.senha like %?2% and c.tipoConta like concat(?3,'%')")
   ContaEntity findByDocumentoESenha(String documento, String senha, Integer tipoConta);
}