package br.edu.infnet.controller;

import br.edu.infnet.model.Pedido;
import br.edu.infnet.service.PedidoQueryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoQueryController {

    private final PedidoQueryService service;

    public PedidoQueryController(PedidoQueryService service) {
        this.service = service;
    }

    // ----------------------------------------------------------
    // LISTAR EVENTOS DO PEDIDO (Query do EventStore)
    // ----------------------------------------------------------
    @Operation(
            summary = "Lista todos os eventos associados ao pedido",
            tags = "Eventos"
    )
    @GetMapping("/{id}/eventos")
    public List<Object> listarEventos(@PathVariable(value = "id") String id){
        return service.listarEventos(id);
    }

    // ----------------------------------------------------------
    // OBTER PEDIDO POR ID (Query)
    // ----------------------------------------------------------
    @Operation(
            summary = "Obtém o pedido pelo ID (estado atual)",
            tags = "Eventos"
    )
    @GetMapping("/{id}")
    public Pedido obterPorId(@PathVariable(value = "id") String id){
        return service.obterPorId(id).get();
    }
}
