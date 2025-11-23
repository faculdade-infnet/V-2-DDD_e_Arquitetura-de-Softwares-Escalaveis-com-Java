package br.edu.infnet.command;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AtualizarPedidoCommand {

    private String id;
    private List<String> itens;
}
