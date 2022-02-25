package br.com.gerenciadorclientesjava.db.contracts;

import br.com.gerenciadorclientesjava.db.entities.ClienteEntity;
import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioContaEntity extends JpaRepository<ContaEntity, Long>{

   @Query(value = "select conta from ContaEntity conta" +
           "   JOIN conta.clienteEntity cliente" +
           "   WHERE cliente.documento = :documento")
   List<ContaEntity> findByDocumento(@Param("documento") String documento);

   @Query(value = "select count(conta) from ContaEntity conta" +
           "   JOIN conta.clienteEntity cliente" +
           "   WHERE cliente.documento = :documento" +
           "   AND conta.tipoConta = :tipoConta")
   Long findByDuplicates(@Param("documento") String Documento, @Param("tipoConta") String tipoConta);
}