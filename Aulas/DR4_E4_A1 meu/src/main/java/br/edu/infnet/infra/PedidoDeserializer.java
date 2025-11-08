package br.edu.infnet.infra;

import br.edu.infnet.domain.*;
import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.*;

// Converte o JSON em objeto
public class PedidoDeserializer extends StdDeserializer<Pedido> {

    public PedidoDeserializer() {
        super(Pedido.class);
    }

    @Override
    public Pedido deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JacksonException {
        JsonNode node = jp.getCodec().readTree(jp);
        Pedido pedido = new Pedido();

        // id
        if (node.has("id") && !node.get("id").isNull()) {
            pedido.setId(node.get("id").asLong());
        }

        // dataPedido
        if (node.has("dataPedido") && !node.get("dataPedido").isNull()) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                pedido.setDataPedido(sdf.parse(node.get("dataPedido").asText()));
            } catch (ParseException e) {
                throw new IOException("Erro ao parsear dataPedido", e);
            }
        }

        // idCliente
        if (node.has("idCliente") && !node.get("idCliente").isNull()) {
            pedido.setIdCliente(node.get("idCliente").asLong());
        }

        // status
        JsonNode statusNode = node.get("status");
        if (statusNode != null && !statusNode.isNull()) {
            pedido.setStatus(Pedido.PedidoStatus.valueOf(statusNode.asText()));
        }

        // valorTotal - usa BigDecimal.valueOf para preservar precisão
        if (node.has("valorTotal") && !node.get("valorTotal").isNull()) {
            pedido.setValorTotal(new ValorMonetario(BigDecimal.valueOf(node.get("valorTotal").asDouble())));
        }

        return pedido;
    }
}
