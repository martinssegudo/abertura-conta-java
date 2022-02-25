package br.com.gerenciadorclientesjava.db.contracts;


import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import br.com.gerenciadorclientesjava.db.entities.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioLoginEntity extends JpaRepository<LoginEntity, Long> {

    @Query(value = "select conta from ContaEntity conta" +
            "   JOIN conta.clienteEntity cliente" +
            "   JOIN cliente.loginEntity login" +
            "   WHERE login.senha = :senha" +
            "   AND cliente.documento = :documento" +
            "   AND conta.tipoConta = :tipoConta")
    ContaEntity findByDocumentoESenha(@Param("documento") String Documento, @Param("senha") String senha, @Param("tipoConta")String tipoConta);

}
