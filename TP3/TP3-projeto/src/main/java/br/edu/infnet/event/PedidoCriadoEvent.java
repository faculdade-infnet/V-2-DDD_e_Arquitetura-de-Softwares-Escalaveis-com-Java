package br.edu.infnet.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PedidoCriadoEvent extends BaseEvent<String> {

    private final String clienteId;
    private final List<String> itens;
    private final Double valorTotal;

    public PedidoCriadoEvent(String id, String clienteId, List<String> itens, Double valorTotal) {
        super(id);
        this.clienteId = clienteId;
        this.itens = itens;
        this.valorTotal = valorTotal;
    }
}