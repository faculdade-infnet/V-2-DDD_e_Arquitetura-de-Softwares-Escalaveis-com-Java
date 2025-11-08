package br.edu.infnet.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "itenspedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;
    private ValorMonetario totalItem;
    private Long idProduto;
    @JoinColumn(name = "PEDIDO_ID", referencedColumnName = "ID")
    @ManyToOne
    private Pedido pedido;

    public ItemPedido() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public ValorMonetario getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(ValorMonetario totalItem) {
        this.totalItem = totalItem;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "id=" + id +
                ", quantidade=" + quantidade +
                ", totalItem=" + totalItem +
                ", idProduto=" + idProduto +
                ", pedido=" + pedido +
                '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (this == null) {
            return true;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ItemPedido other = (ItemPedido) obj;
        return Objects.equals(this.id, other.id);
    }
}
