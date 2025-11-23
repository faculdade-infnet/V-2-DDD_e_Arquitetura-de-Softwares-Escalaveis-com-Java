package br.edu.infnet.contas.infra;

import br.edu.infnet.contas.domain.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, String> {
}
