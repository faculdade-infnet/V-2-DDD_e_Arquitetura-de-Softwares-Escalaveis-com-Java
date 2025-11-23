package br.edu.infnet.command;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CriarPedidoCommand {

    private Long id;
    private Integer clienteId;
    private List<String> itens;
}
