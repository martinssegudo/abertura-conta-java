package br.com.gerenciadorclientesjava.db.contracts;

import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioContaEntity extends JpaRepository<ContaEntity, Long>{

   @Query(value = "select conta from ContaEntity conta" +
           "   JOIN conta.clienteEntity cliente" +
           "   WHERE cliente.documento = :documento")
   Optional<List<ContaEntity>> findByDocumento(@Param("documento") String documento);

   @Query(value = "select conta from ContaEntity conta" +
           "   JOIN conta.clienteEntity cliente" +
           "   WHERE cliente.documento = :documento" +
           "   AND conta.tipoConta = :tipoConta")
   Optional<ContaEntity> findByDuplicates(@Param("documento") String Documento, @Param("tipoConta") String tipoConta);

   @Query(value = "select conta from ContaEntity conta" +
           "   JOIN conta.clienteEntity cliente" +
           "   JOIN cliente.loginEntity login" +
           "   WHERE login.senha = :senha" +
           "   AND cliente.documento = :documento" +
           "   AND conta.tipoConta = :tipoConta")
   Optional<ContaEntity> findByDocumentoESenha(@Param("documento") String documento, @Param("senha") String senha, @Param("tipoConta")String tipoConta);

}