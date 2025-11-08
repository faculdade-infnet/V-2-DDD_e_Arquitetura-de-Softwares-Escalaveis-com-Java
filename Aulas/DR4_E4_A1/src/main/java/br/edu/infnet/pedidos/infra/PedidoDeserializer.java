package br.edu.infnet.pedidos.infra;

import br.edu.infnet.pedidos.domain.Pedido;
import br.edu.infnet.pedidos.domain.ValorMonetario;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PedidoDeserializer extends StdDeserializer<Pedido> {

    public PedidoDeserializer() {
        super(Pedido.class);
    }

    @Override
    public Pedido deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JacksonException {
        JsonNode node = jp.getCodec().readTree(jp);
        Pedido pedido = new Pedido();
        pedido.setId(node.get("id").asLong());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            pedido.setOrderDate(sdf.parse(node.get("orderDate").asText()));
        } catch (ParseException e) {
            throw new IOException("Erro na data");
        }
        pedido.setCustomerId(node.get("customerId").asLong());
        pedido.setStatus(Pedido.PedidoStatus.valueOf(node.get("status").asText()));
        pedido.setValorTotal(new ValorMonetario(new BigDecimal(node.get("valorTotal").asDouble())));
        return pedido;
    }
}
