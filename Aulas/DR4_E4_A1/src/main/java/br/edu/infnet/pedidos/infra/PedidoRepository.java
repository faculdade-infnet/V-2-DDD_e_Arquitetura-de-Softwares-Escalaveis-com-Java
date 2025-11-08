package br.edu.infnet.pedidos.infra;

import br.edu.infnet.pedidos.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository  extends JpaRepository<Pedido, Long> {
}
