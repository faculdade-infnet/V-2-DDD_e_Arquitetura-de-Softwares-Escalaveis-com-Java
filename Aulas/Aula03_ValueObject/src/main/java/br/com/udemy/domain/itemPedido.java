package br.com.udemy.domain;

import java.util.Objects;

@Enitty
@Table(name = "itenspedido")
public class itemPedido {
    @Id
    private Long id;
    private Integer quantidade;
    private ValorMonetario totalItem;
    private Long idProduto;
    @JoinCollumn(name = "PEDIDO_ID", referenceCollumnName = "ID")
    @ManyToOne
    private Pedido pedido;

    public ItemPedido(){
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof itemPedido that)) return false;
        return Objects.equals(id, that.id) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
