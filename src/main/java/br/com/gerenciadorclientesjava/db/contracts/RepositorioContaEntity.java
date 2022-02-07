package br.com.gerenciadorclientesjava.db.contracts;

import br.com.gerenciadorclientesjava.db.entities.ContaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioContaEntity extends JpaRepository<ContaEntity, Long> {
   // public ContaEntity findByEmail(String email);
}