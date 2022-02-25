package br.com.gerenciadorclientesjava.db.contracts;

import br.com.gerenciadorclientesjava.db.entities.ClienteEntity;
import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioClienteEntity extends JpaRepository<ClienteEntity, Long> {

    //List<ContaEntity> findByDocumento(String documento);



}
