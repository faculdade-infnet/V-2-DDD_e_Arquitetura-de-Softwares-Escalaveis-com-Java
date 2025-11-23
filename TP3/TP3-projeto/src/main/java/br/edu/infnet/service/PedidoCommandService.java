package br.edu.infnet.service;

import br.edu.infnet.model.Pedido;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface PedidoCommandService {

    CompletableFuture<String> criarPedido(Pedido pedido);

    CompletableFuture<String> atualizarPedido(String id, List<String> itens );

    CompletableFuture<String> cancelarPedido(String id);
}
