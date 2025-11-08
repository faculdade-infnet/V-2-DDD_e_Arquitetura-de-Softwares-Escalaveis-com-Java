package br.edu.infnet.pedidos.eventos;

import br.edu.infnet.pedidos.domain.Pedido;
import java.io.Serializable;
import java.util.Date;

public class EstadoPedidoMudou implements Serializable {
    
    Long idPedido;
    Pedido.PedidoStatus estado;
    Date momento;

    public EstadoPedidoMudou(Long idPedido, Pedido.PedidoStatus estado, Date momento) {
        this.idPedido = idPedido;
        this.estado = estado;
        this.momento = momento;
    }

    public Long getIdPedido() {
        return idPedido;
    }

    public Pedido.PedidoStatus getEstado() {
        return estado;
    }
    
    public Date quandoMudou() {
        return this.momento;
    }    
}
