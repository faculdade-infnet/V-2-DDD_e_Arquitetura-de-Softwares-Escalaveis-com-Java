package br.edu.infnet.command;

import br.edu.infnet.model.Pedido;
import br.edu.infnet.repository.PedidoRepository;
import org.axonframework.commandhandling.CommandHandler;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PedidoCommandHandler {

    private final PedidoRepository pedidoRepository;

    public PedidoCommandHandler(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @CommandHandler
    public void handle(CriarPedidoCommand command) {
        Pedido pedido = new Pedido();
        pedido.setId(Long.valueOf(command.getId()));
        pedido.setClienteId(command.getClienteId());
        pedido.setItens(command.getItens());
        pedido.setStatus(br.edu.infnet.model.StatusPedido.NOVO);

        pedidoRepository.save(pedido);
    }

    @CommandHandler
    public void handle(AtualizarPedidoCommand command) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(Long.valueOf(command.getId()));
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.setItens(command.getItens());
            pedidoRepository.save(pedido);
        } else {
            throw new IllegalArgumentException("Pedido não encontrado: " + command.getId());
        }
    }

    @CommandHandler
    public void handle(CancelarPedidoCommand command) {
        Optional<Pedido> pedidoOpt = pedidoRepository.findById(Long.valueOf(command.getId()));
        if (pedidoOpt.isPresent()) {
            Pedido pedido = pedidoOpt.get();
            pedido.setStatus(br.edu.infnet.model.StatusPedido.CANCELADO);
            pedidoRepository.save(pedido);
        } else {
            throw new IllegalArgumentException("Pedido não encontrado: " + command.getId());
        }
    }
}
