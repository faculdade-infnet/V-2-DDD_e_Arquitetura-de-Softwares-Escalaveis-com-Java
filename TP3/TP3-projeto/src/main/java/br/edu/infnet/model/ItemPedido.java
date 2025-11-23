package br.edu.infnet.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "ITEM_PEDIDO", catalog = "TP3")
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    private Integer quantity;

    @Column(precision = 20, scale = 2)
    private BigDecimal total;

    @Column(name = "PRODUCT_ID")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ID")
    private Pedido pedido;
}
