package br.edu.infnet.contas.services;

import br.edu.infnet.contas.domain.ContaCorrente;
import br.edu.infnet.contas.infra.ContaCorrenteRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContaCorrenteQueryServiceImpl implements ContaCorrenteQueryService {

    private final EventStore eventStore;
    private final ContaCorrenteRepository contaCorrenteRepository;

    @Autowired
    public ContaCorrenteQueryServiceImpl(EventStore eventStore, ContaCorrenteRepository contaCorrenteRepository) {
        this.eventStore = eventStore;
        this.contaCorrenteRepository = contaCorrenteRepository;
    }

    @Override
    public List<Object> listarEventos(String id) {
        List<Object> retorno = eventStore.readEvents(id, 0)
                .asStream()
                .map(record -> record.getPayload()).collect(Collectors.toList());
        return retorno;
    }

    public Optional<ContaCorrente> obterPorId(String id) {
        return contaCorrenteRepository.findById(id);
    }
}
