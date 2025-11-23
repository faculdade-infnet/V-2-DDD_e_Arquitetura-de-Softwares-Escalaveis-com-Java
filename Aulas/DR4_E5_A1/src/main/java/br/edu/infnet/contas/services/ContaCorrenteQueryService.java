package br.edu.infnet.contas.services;

import br.edu.infnet.contas.domain.ContaCorrente;
import java.util.List;
import java.util.Optional;

public interface ContaCorrenteQueryService {
    public Optional<ContaCorrente> obterPorId(String id);
    public List<Object> listarEventos(String id);
}
