package br.edu.infnet.controller;

import br.edu.infnet.model.Pedido;
import br.edu.infnet.service.PedidoCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoCommandController {

    private final PedidoCommandService service;

    // ----------------------------------------------------------
    // CRIAR PEDIDO
    // ----------------------------------------------------------

    @Operation(
            summary = "Cria um novo pedido",
            tags = "Pedidos",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    content = @Content(
                            mediaType = "application/json",
                            examples = {
                                    @ExampleObject(
                                            name = "Exemplo de Pedido",
                                            value = "{\n" +
                                                    "  \"id\": 0,\n" +
                                                    "  \"clienteId\": 0,\n" +
                                                    "  \"dataCriacao\": \"23/11/2025\",\n" +
                                                    "  \"valorTotal\": 102.53,\n" +
                                                    "  \"status\": \"NOVO\",\n" +
                                                    "  \"itens\": [\"camiseta\", \"tênis\", \"calça\"]\n" +
                                                    "}"
                                    )
                            }
                    )
            )
    )
    @PostMapping
    public CompletableFuture<String> criarPedido(@RequestBody Pedido pedido) {
        return service.criarPedido(pedido);
    }

    // ----------------------------------------------------------
    // ATUALIZAR PEDIDO
    // ----------------------------------------------------------
    @Operation(summary = "Atualiza um pedido existente", tags = "Pedidos")
    @PutMapping("/{id}")
    public CompletableFuture<String> atualizarPedido(
            @PathVariable String id,
            @RequestBody Map<String, Object> atualizar) {
        return service.atualizarPedido(id, (List<String>) atualizar.get("itens"));
    }


    // ----------------------------------------------------------
    // CANCELAR PEDIDO
    // ----------------------------------------------------------
    @Operation(summary = "Cancela um pedido", tags = "Pedidos")
    @DeleteMapping("/{id}")
    public CompletableFuture<String> cancelarPedido(@RequestBody Map<String, Object> cancelar) {
        return service.cancelarPedido((String) cancelar.get("id"));
    }
}