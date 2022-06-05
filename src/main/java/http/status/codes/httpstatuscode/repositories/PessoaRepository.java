package http.status.codes.httpstatuscode.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import http.status.codes.httpstatuscode.domain.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    Optional<Pessoa> findByNome(String name);

    Integer countByNome(String nome);
}
