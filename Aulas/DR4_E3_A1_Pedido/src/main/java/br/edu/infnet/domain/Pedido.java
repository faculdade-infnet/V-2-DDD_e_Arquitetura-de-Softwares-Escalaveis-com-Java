package br.edu.infnet.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date dataPedido;
    @OneToMany
    private List<ItemPedido> itensPedido;
    private Long idCliente;
    private ValorMonetario valorTotal;
    private PedidoStatus status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public ValorMonetario getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(ValorMonetario valorTotal) {
        this.valorTotal = valorTotal;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", dataPedido=" + dataPedido + ", itensPedido=" + itensPedido + ", idCliente=" + idCliente + ", valorTotal=" + valorTotal + '}';
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
        Pedido other = (Pedido) obj;
        return Objects.equals(this.id, other.id);
    }

    public enum PedidoStatus {
        NOVO, FECHADO, CANCELADO, ENVIADO
    }

    public void adicionarItem(Long idProduto, int quantidade) {
        if (idProduto == null) {
            throw new IllegalArgumentException("Produto inválido");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade precisa ser positiva");
        }
        if (this.status != PedidoStatus.NOVO) {
            throw new IllegalStateException("Não é possível inserir itens em um pedido em andamento");
        }
        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setPedido(this);
        itemPedido.setIdProduto(idProduto);
        itemPedido.setQuantidade(quantidade);
        if (this.itensPedido == null) {
            this.itensPedido = new ArrayList<>();
        }
        this.itensPedido.add(itemPedido);
    }

    // Métodos de Negócio
    public void fecharPedido() {
        if (this.status != PedidoStatus.NOVO) {
            throw new IllegalStateException("Não é possível fechar um pedido que não é novo");
        }
        if (this.itensPedido.isEmpty()) {
            throw new IllegalStateException("Não é possível fechar um pedido vazio");
        }
        this.status = PedidoStatus.FECHADO;
        //DomainEvents.publish(new PedidoFechadoEvent(this.id);
    }

    public void cancelarPedido() {
        if (this.status != PedidoStatus.FECHADO) {
            throw new IllegalStateException("Não é possível cancelar um pedido que não esteja fechado");
        }
        this.status = PedidoStatus.CANCELADO;
        //DomainEvents.publish(new PedidoCanceladoEvent(this.id);
    }

    public void enviarPedido() {
        if (this.status != PedidoStatus.FECHADO) {
            throw new IllegalStateException("Não é possível enviar um pedido que não esteja fechado");
        }
        this.status = PedidoStatus.ENVIADO;
        //DomainEvents.publish(new PedidoEnviadoEvent(this.id);
    }
}