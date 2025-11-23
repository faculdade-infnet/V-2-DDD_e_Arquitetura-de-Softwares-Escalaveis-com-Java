package br.edu.infnet.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PedidoAtualizadoEvent extends BaseEvent<String> {

    private final List<String> itens;

    public PedidoAtualizadoEvent(String pedidoId, List<String> itens) {
        super(pedidoId);
        this.itens = itens;
    }
}