package br.com.projeto.crud.repository;

import br.com.projeto.crud.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrudRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByNome(String nome);

}
