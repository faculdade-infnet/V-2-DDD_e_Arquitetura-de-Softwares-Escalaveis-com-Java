package br.edu.infnet.service;

import br.edu.infnet.model.Pedido;
import br.edu.infnet.repository.PedidoRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoQueryServiceImpl implements PedidoQueryService {

    private final EventStore eventStore;
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoQueryServiceImpl(EventStore eventStore, PedidoRepository pedidoRepository) {
        this.eventStore = eventStore;
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Object> listarEventos(String id) {
        List<Object> retorno = eventStore.readEvents(id, 0)
                .asStream()
                .map(record -> record.getPayload()).collect(Collectors.toList());
        return retorno;
    }

    public Optional<Pedido> obterPorId(String id) {
        return pedidoRepository.findById(Long.valueOf(id));
    }
}
