package br.edu.infnet.infra;

import br.edu.infnet.eventos.EstadoPedidoMudou;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class EstadoPedidoMudouSerializer extends StdSerializer<EstadoPedidoMudou> {
    
    public EstadoPedidoMudouSerializer() {
        super(EstadoPedidoMudou.class);
    }

    @Override
    public void serialize(EstadoPedidoMudou evento, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeNumberField("idPedido", evento.getIdPedido());
        jgen.writeStringField("estado", evento.getEstado().toString());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        jgen.writeStringField("momento", sdf.format(evento.quandoMudou()));
    }    
}
