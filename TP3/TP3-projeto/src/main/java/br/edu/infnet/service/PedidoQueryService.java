package br.edu.infnet.service;

import br.edu.infnet.model.Pedido;
import java.util.List;
import java.util.Optional;

public interface PedidoQueryService {
    public Optional<Pedido> obterPorId(String id);

    public List<Object> listarEventos(String id);
}
