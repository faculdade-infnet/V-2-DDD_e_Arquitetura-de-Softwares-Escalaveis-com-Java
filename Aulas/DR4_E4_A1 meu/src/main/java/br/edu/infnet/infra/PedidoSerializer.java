package br.edu.infnet.infra;

import br.edu.infnet.domain.Pedido;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.SimpleDateFormat;

// Converte o Objeto pedio em JSON
public class PedidoSerializer extends StdSerializer<Pedido> {

    public PedidoSerializer() {
        super(Pedido.class);
    }

    @Override
    public void serialize(Pedido pedido, JsonGenerator jgen, SerializerProvider provider) throws IOException {

        jgen.writeStartObject();

        // id
        if (pedido.getId() != null) {
            jgen.writeNumberField("id", pedido.getId());
        }

        // dataPedido
        if (pedido.getDataPedido() != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            jgen.writeStringField("dataPedido", sdf.format(pedido.getDataPedido()));
        }

        // idCliente
        if (pedido.getIdCliente() != null) {
            jgen.writeNumberField("idCliente", pedido.getIdCliente());
        }

        // status
        if (pedido.getStatus() != null) {
            jgen.writeStringField("status", pedido.getStatus().name());
        }

        // valorTotal - usa toPlainString para preservar precisão
        if (pedido.getValorTotal() != null && pedido.getValorTotal().getQuantia() != null) {
            jgen.writeStringField("valorTotal", pedido.getValorTotal().getQuantia().toPlainString());
        }

        jgen.writeEndObject();
    }
}
