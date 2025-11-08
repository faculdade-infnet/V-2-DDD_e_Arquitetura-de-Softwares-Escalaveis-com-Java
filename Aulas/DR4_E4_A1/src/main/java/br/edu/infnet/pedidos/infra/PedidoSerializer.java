package br.edu.infnet.pedidos.infra;

import br.edu.infnet.pedidos.domain.Pedido;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class PedidoSerializer extends StdSerializer<Pedido> {

    public PedidoSerializer() {
        super(Pedido.class);
    }

    @Override
    public void serialize(Pedido pedido, JsonGenerator jgen, SerializerProvider provider) throws IOException {

        jgen.writeStartObject();
        jgen.writeNumberField("id", pedido.getId());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        jgen.writeStringField("orderDate", sdf.format(pedido.getOrderDate()));
        jgen.writeNumberField("customerId", pedido.getCustomerId());
        jgen.writeStringField("status", pedido.getStatus().toString());
        jgen.writeNumberField("valorTotal", pedido.getValorTotal().getQuantia().doubleValue());
    }

}
