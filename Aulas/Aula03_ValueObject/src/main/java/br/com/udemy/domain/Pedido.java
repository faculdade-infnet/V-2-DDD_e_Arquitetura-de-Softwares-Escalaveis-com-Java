package br.com.udemy.domain;

import br.com.udemy.model.Person;

@Enitty
@Table(name = "pedidos")
public class Pedido {
    @Id
    private Long id;
    @Temporal(TemporalType.DATE)
    private Integer quantidade;
    private ValorMonetario totalItem;
    private Long idProduto;


    public enum PedidoStatus {
        NOVO, FECAHDO, CANCELADO, ENVIADO
    }

    public void adioconarItem(Long idProduto, int quantidade) {
        if (idProduto == null) {
            throw new IllegalArgumentException("Produto Inválido");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Aquantidade precisa ser positiva");
        }
        if (this.status != PedidoStatus.NOVO) {
            throw new IllegalArgumentException("NÃO É POSSÍVEL INSERIR ITENS EM UM PEDIDO EM ANADAMENTO");
        }

        ItemPedido.itemPedido = new itemPedido();
        ItemPedido.setOrderId(this);


    }

    public void fecharPedido() {
        if (this.status != PedidoStatus.NOVO) {
            throw new IllegalArgumentException("Não é possível fechar um pedido que não é novo");
        }
        if (this.itensPedido == null || this.itensPedido.isEmpty()) {
            throw new IllegalArgumentException("Não é possível fechar um pedido vazio");
        }
        this.status = PedidoStatus.FECHADO;
        // DomainsEvents.publish(new  PedidoiFechadoEvent(this.id);
    }

    public void cancelarPedido() {
        if (this.status != PedidoStatus.FECAHDO) {
            throw new IllegalArgumentException("Não é possível cancelar um pedido que não esteja fechado");
        }
        this.status = PedidoStatus.CANCELADO;
        // DomainsEvents.publish(new  PedidoiCanceladoEvent(this.id);
    }

    public void enviarPedido() {
        if (this.status != PedidoStatus.FECAHDO) {
            throw new IllegalArgumentException("Não é possível enviar um pedido que não esteja fechado");
        }
        this.status = PedidoStatus.ENVIADO;
        // DomainsEvents.publish(new  PedidoEnviadoEvent(this.id);
    }
}
