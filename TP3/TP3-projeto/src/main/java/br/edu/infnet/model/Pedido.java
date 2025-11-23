package br.edu.infnet.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(name = "ID_CLIENTE")
    private Integer clienteId;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @Column(name = "DATA_DE_CRIACAO")
    private LocalDate dataCriacao;

    //@Schema(example = "102.53")
    @Column(name = "VALOR_TOTAL")
    private Double valorTotal;

    @Enumerated(EnumType.STRING)
    private StatusPedido status;

    //@Schema(description = "Lista de itens do pedido", example = "[\"camiseta\", \"tênis\", \"calça\"]")
    @ElementCollection
    @CollectionTable(name = "pedido_itens", joinColumns = @JoinColumn(name = "pedido_id"))
    private List<String> itens;
}
