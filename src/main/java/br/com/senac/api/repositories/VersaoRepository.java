package br.com.senac.api.repositories;

import br.com.senac.api.entities.Versao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VersaoRepository extends JpaRepository<Versao, Long> {
}
