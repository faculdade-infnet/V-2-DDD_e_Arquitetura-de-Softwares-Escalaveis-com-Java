package br.edu.infnet.service;

import br.edu.infnet.command.AtualizarPedidoCommand;
import br.edu.infnet.command.CancelarPedidoCommand;
import br.edu.infnet.command.CriarPedidoCommand;
import br.edu.infnet.model.Pedido;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class PedidoCommandServiceImpl implements PedidoCommandService {
    private final CommandGateway commandGateway;

    @Autowired
    public PedidoCommandServiceImpl(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @Override
    public CompletableFuture<String> criarPedido(Pedido pedido) {
        UUID uuid = UUID.randomUUID();

        return commandGateway.send(new CriarPedidoCommand(
                Math.abs(uuid.getMostSignificantBits()),
                pedido.getClienteId(),
                pedido.getItens()));
    }

    @Override
    public CompletableFuture<String> atualizarPedido(String id, List<String> itens) {
        return commandGateway.send(new AtualizarPedidoCommand(
                id,
                itens
        ));
    }

    @Override
    public CompletableFuture<String> cancelarPedido(String id) {
        return commandGateway.send(new CancelarPedidoCommand(id));
    }
}
