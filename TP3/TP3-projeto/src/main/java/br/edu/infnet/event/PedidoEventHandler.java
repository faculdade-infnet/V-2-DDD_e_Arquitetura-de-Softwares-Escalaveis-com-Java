package br.edu.infnet.event;

import br.edu.infnet.model.Pedido;
import br.edu.infnet.model.StatusPedido;
import br.edu.infnet.repository.PedidoRepository;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class PedidoEventHandler {

    private final PedidoRepository pedidoRepository;

    public PedidoEventHandler(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @EventHandler
    public void on(PedidoCriadoEvent event) {
        Pedido pedido = new Pedido();
        pedido.setId(Long.valueOf(event.getId()));
        pedido.setClienteId(Integer.valueOf(event.getClienteId()));
        pedido.setItens(event.getItens());
        pedido.setValorTotal(event.getValorTotal());
        pedido.setStatus(StatusPedido.NOVO);

        pedidoRepository.save(pedido);
    }

    @EventHandler
    public void on(PedidoAtualizadoEvent event) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(Long.valueOf(event.getId()));
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.setItens(event.getItens());
            pedidoRepository.save(pedido);
        }
    }

    @EventHandler
    public void on(PedidoCanceladoEvent event) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(Long.valueOf(event.getId()));
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.setStatus(StatusPedido.CANCELADO);
            pedidoRepository.save(pedido);
        }
    }
}