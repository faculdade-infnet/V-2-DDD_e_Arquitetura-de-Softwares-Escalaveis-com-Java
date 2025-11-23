package br.edu.infnet.event;

import lombok.Getter;

@Getter
public class PedidoCanceladoEvent extends BaseEvent<String> {
    public PedidoCanceladoEvent(String pedidoId) {
        super(pedidoId);
    }
}