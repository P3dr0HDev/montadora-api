package br.com.senac.api.repositories;

import br.com.senac.api.entities.Montadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MontadoraRepository extends JpaRepository<Montadora, Long> {

    //List<Montadora> findByNome(String nome);
}
